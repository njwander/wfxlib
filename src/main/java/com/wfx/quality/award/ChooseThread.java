package com.wfx.quality.award;

import java.util.Random;

/**
 * 定义的线程类,该线呈是循环的显示工号
 * Created by admin on 14-9-2.
 */
class ChooseThread extends Thread
{
    private boolean runFlag = true;//决定此线程是否运行的标记
    //需要该对象来读取文本框字段，不用创建它，申明下就好
    private ChooseAward chooseAward = null;
    //创建一个新的随机数生成器
    Random randomNumber = new Random();

    public ChooseThread(Object obj)
    {
        start();
        chooseAward = (ChooseAward) obj;
    }

    public void start()
    {
        runFlag = false;
        super.start();
    }

    public void changeflag_start()
    {
        runFlag = true;
    }

    public void changeflag_stop()
    {
        runFlag = false;
    }

    /*
     *实现文本框滚动的效果
     */
    public void run()
    {
        while (runFlag)
        {
            //返回向量中存储了几个号码的随便一个序号
            int num = randomNumber.nextInt(chooseAward.vJobNumber.size());
            //显示那个选种的序号对应的号码
            chooseAward.jobName.setText((String) chooseAward.vJobName
                    .elementAt(num));
            chooseAward.jobNumber.setText((String) chooseAward.vJobNumber
                    .elementAt(num));
            try
            {
                sleep(50);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}