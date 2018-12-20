/**
 * @author:
 * @Description:
 * @Data: 2018/12/20 11:34
 **/
package com.hotspot;

/**
 * VM Args: -Xss128k
 * 设置栈容量128K，栈容量只由 -Xss 设置
 */
public class JavaVMStackSOF
{
    private int stackLength = 1;

    /**
     * 栈泄漏. 导致 java.lang.StackOverflowError 异常
     */
    public void stackLeak()
    {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args)
    {
        JavaVMStackSOF oom = new JavaVMStackSOF();

        try
        {
            oom.stackLeak();
        }
        catch(Throwable e)
        {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
