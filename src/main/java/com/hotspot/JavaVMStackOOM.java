/**
 * @author:
 * @Description:
 * @Data: 2018/12/20 13:53
 **/
package com.hotspot;

/**
 * VM Args: -Xss2m
 * 设置栈容量2m，栈容量只由 -Xss 设置
 */
public class JavaVMStackOOM
{
    private void dontStop()
    {
        while(true)
        {

        }
    }

    /**
     * 多线程栈泄漏 导致 OOM 异常；不建议运行，容易导致系统假死
     */
    public void stackLeakByThread()
    {
        Thread thread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                dontStop();
            }
        });
    }

    public static void main(String[] args)
    {
        JavaVMStackOOM javaVMStackOOM = new JavaVMStackOOM();
        // javaVMStackOOM.stackLeakByThread();
    }
}
