package com.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.wayto.crm.annotation.Export;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;

/**
 * 导出工具类
 *
 * @author hzl
 *
 */
@Log4j2
public class ExportUtil {

    private static final String INTEGER = "java.lang.Integer";
    private static final String STRING = "java.lang.String";
    private static final String LOCALDATETIME = "java.time.LocalDateTime";
    private static final String CONTENT_DISPOSITION = "Content-Disposition";
    private static final String APPLICATION_FORCE_DOWNLOAD = "application/force-download";

    /**
     * 设置下载头
     *
     * @param fileName
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void setHeader(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        response.setContentType(APPLICATION_FORCE_DOWNLOAD);// 设置强制下载不打开            
        response.addHeader(CONTENT_DISPOSITION, "attachment;fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
        response.addHeader("Access-Control-Expose-Headers", CONTENT_DISPOSITION);
    }

    /**
     * 写入输出流
     * @param exportObj
     * @param exportParams
     * @param list
     * @return
     */
    public static void writeOutputStream(Object exportObj, ExportParams exportParams, List<Map<String, Object>> list,
                                         HttpServletResponse response) {
        List<ExcelExportEntity> excelExportEntities = ExportUtil.getExportEntity(exportObj);
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, excelExportEntities, list);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置导出excel的表格头标题与各个字段对应关系
     * @param exportObj
     * @return
     */
    private static List<ExcelExportEntity> getExportEntity(Object exportObj) {

        Class<? extends Object> exportObjClass = exportObj.getClass();
        Field[] fields = exportObjClass.getDeclaredFields();
        List<ExcelExportEntity> entities = new ArrayList<>();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                // 获取字段导出注解
                Export exportAnnotation = field.getAnnotation(Export.class);
                if(exportAnnotation == null || exportAnnotation.boolIgnore()) {
                    continue;
                }
                String fieldName = field.getName();
                ExcelExportEntity entity = new ExcelExportEntity(exportAnnotation.titleName(), fieldName, exportAnnotation.width());
                entities.add(entity);
            } catch (IllegalArgumentException | SecurityException e) {
                log.error(e.getMessage(), e);
            }
        }

        return entities;
    }

    /**
     * 对象复制
     *
     * @param source
     * @param target
     */
    public static void copyProperties(Object source, Object target) {
        if (source == null) {
            return;
        }

        Class<? extends Object> sourceClass = source.getClass();
        Field[] targetFields = target.getClass().getDeclaredFields();
        for (Field targetField : targetFields) {
            String name = targetField.getName();
            try {
                Field sourceField = sourceClass.getDeclaredField(name);
                sourceField.setAccessible(true);
                targetField.setAccessible(true);
                Object value = sourceField.get(source);

                // 数据类型转换
                if (sourceField.getType().getName().equals(INTEGER)
                        && targetField.getType().getName().equals(STRING)) {
                    if (value != null) {
                        targetField.set(target, String.valueOf(value));
                    }
                } else if (sourceField.getType().getName().equals(STRING)
                        && targetField.getType().getName().equals(INTEGER)) {
                    if (value != null) {
                        targetField.set(target, Integer.valueOf(value.toString()));
                    }
                } else if(sourceField.getType().getName().equals(LOCALDATETIME)
                        && targetField.getType().getName().equals(STRING)){
                    targetField.set(target, value != null ? String.valueOf(value.toString()) : "");
                } else {
                    targetField.set(target, value);
                }
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
                log.info(e.getMessage());
            }
        }
    }

    /**
     * 将需要转化的字段进行转化
     *
     * @param exportFiled
     */
    public static <T> void setExportFieldValue(T exportFiled) {
        Class<? extends Object> exportFiledClass = exportFiled.getClass();
        Field[] fields = exportFiledClass.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldName = field.getName();
                Field declaredField = exportFiledClass.getDeclaredField(fieldName);
                declaredField.setAccessible(true);
                Object object = declaredField.get(exportFiled);
                if (object == null) {
                    continue;
                }

                // 不需要转化则不变
                field.setAccessible(true);
                Export annotation = field.getAnnotation(Export.class);
                if(annotation == null || !annotation.boolReplace()) {
                    continue;
                }

                // 将数字值转换为中文值
                Object value = field.get(exportFiled);
                String valueStr = value.toString();
                Optional<String> findAny = Arrays.stream(annotation.replaceArray())
                        .filter(v ->
                            v.split(":")[0].equals(valueStr)
                        ).findFirst();
                if (findAny.isPresent()) {
                    value = findAny.get().substring(valueStr.length() + 1);
                    field.set(exportFiled, value);
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                log.error(e.getMessage(), e);
            }
        }

    }
}
