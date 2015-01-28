package com.wfx.quality.award;

/**
 * 抽奖小程序，通过载入一个特定格式的文件，导入总共的奖品数量，抽奖的范围,支持最终抽奖结果的导出。文件格式如下，需要UTF-8编码：

 * Created by admin on 14-9-2.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

public class ChooseAward extends JFrame {

    JButton b_start = new JButton(new ImageIcon("src/main/resources/image/btn_start.png"));

    JButton b_stop = new JButton(new ImageIcon("src/main/resources/image/btn_stop.png"));

    JButton b_rechoose = new JButton(new ImageIcon("src/main/resources/image/btn_rechoose.png"));


    JPanel p_north = new JPanel();
    JPanel p_center = new JPanel();
    JPanel p_south = new JPanel();

    //菜单栏
    JMenuBar menubar = new JMenuBar();

    //菜单项
    JMenu fileMenu = new JMenu("文件");
    JMenu helpMenu = new JMenu("帮助");

    String filePath = null;

    /*
     * 菜单子项
     */
    private JMenuItem[] filem = {new JMenuItem("选择文件"),
            new JMenuItem("保存"), new JMenuItem("退出")
    };

    //帮助
    private JMenuItem[] helpm = {new JMenuItem("关于")
    };


    JTextField jobName = new JTextField(); // 工号
    JTextField jobNumber = new JTextField(); // 花名

    Vector vJobNumber = new Vector(); // 存放读取出来的工号

    Vector vJobName = new Vector(); // 存放读取出来的人的花名

    Vector<String> v_printident = new Vector<String>(); // 存放需要打印的信息

    JLabel l_information = new JLabel();

    JLabel l_jobNumber = new JLabel();

    JLabel l_sysinformation = new JLabel("系统信息:");

    JFileChooser filechooser = new JFileChooser(); // 文件选择器

    private ImageIcon background;
    private ImageIcon img_bstart;

    Award award = null;

    //选择随机数的线程
    public ChooseThread awardThread = null;

    int chooseTime = 0; // 按下停止按钮的次数,也就是抽奖的次数

    public ChooseAward() {
        super("淘宝技术质量季度会抽奖系统");
        background = new ImageIcon("src/main/resources/image/backgroud.jpg");
        JLabel label = new JLabel(background);
        label.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        jobName.setEditable(false);
        JPanel imagePanel = (JPanel)getContentPane();
        imagePanel.setOpaque(false);
        //BorderLayout布局管理器,东，南，西，北
        imagePanel.setLayout(null);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setOpaque(false);
        b_start.setOpaque(false);
        b_start.setSize(105,38);
        b_start.setBorder(null);
        b_start.setBackground(Color.RED);
        b_start.setFocusPainted(false);
        b_start.setFocusable(false);

        b_stop.setOpaque(false);
        b_stop.setSize(105, 38);
        b_stop.setBorder(null);
        b_stop.setBackground(Color.RED);
        b_stop.setFocusPainted(false);
        b_stop.setFocusable(false);
//        b_stop.setEnabled(false);
        b_rechoose.setOpaque(false);
        b_rechoose.setSize(105,38);
        b_rechoose.setBorder(null);
        b_rechoose.setBackground(Color.RED);
        b_rechoose.setFocusPainted(false);
        b_rechoose.setFocusable(false);
//        b_rechoose.setEnabled(false);

        contentPane.setBounds(background.getIconWidth() / 2 - 200, background.getIconHeight() - 350, 400, 200);
        filem[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_loadident_ActionPerformed(e);
            }
        });
        b_start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_start_ActionPerformed(e);
            }
        });
        b_stop.addActionListener(new ActionListener() {// 注册停止事件的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                b_stop_ActionPerformed(e);
            }
        });
        b_rechoose.addActionListener(new ActionListener() {// 注册停止事件的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                b_rechoose_ActionPerformed(e);
            }
        });
        filem[1].addActionListener(new ActionListener() {// 注册打印事件的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                b_printaward_ActionPerformed(e);
            }
        });
        filem[2].addActionListener(new ActionListener() {// 注册打印事件的监听器
            @Override
            public void actionPerformed(ActionEvent e) {
                b_exit_ActionPerformed(e);
            }
        });
        helpm[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b_about_ActionPerformed(e);
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //将菜单子项加入菜单中
        putItem2Menu(fileMenu, filem);
        putItem2Menu(helpMenu, helpm);

        //将菜单条加入frame中
        menubar.add(fileMenu);
        menubar.add(helpMenu);
        setJMenuBar(menubar);


        /*将p_north设计为一个状态栏，显示一些操作过程中的信息
         * 创建一个左对齐方式的流布局
         */

        p_south.setLayout(new FlowLayout(FlowLayout.LEFT));
        l_information.setForeground(Color.blue);
//        p_south.add(l_sysinformation);
//        p_south.add(l_information);
        contentPane.add(p_south, BorderLayout.SOUTH);

        //设置的一些显示方式，字体，大小等,为了尽量美观一点
        Font xuehao = new Font("华文琥珀", Font.PLAIN, 25);
        l_jobNumber.setFont(xuehao);
        //字体的居中对齐
        l_jobNumber.setHorizontalAlignment(0);
        l_jobNumber.setText("中奖人");
        l_jobNumber.setBorder(null);


        Font number = new Font("幼圆", Font.BOLD, 35);//粗体
        jobName.setFont(number);
        //设置字体颜色
//        jobName.setForeground(Color.red);
        jobName.setHorizontalAlignment(0);
        jobName.setBorder(null);


        //给上面的jpanel设置布局
        p_north.setLayout(new GridLayout(2, 1, 0, 20));
        p_north.add(l_jobNumber);
        p_north.add(jobName);

        contentPane.add(p_north, BorderLayout.NORTH);
        //给中间的jpanel 设置布局
        p_center.setLayout(new GridLayout(1, 2, 7, 0));

        p_center.add(b_start);
        p_center.add(b_stop);
        p_center.add(b_rechoose);

        p_south.setOpaque(false);
        p_north.setOpaque(false);
        p_center.setOpaque(false);
        jobName.setOpaque(false);
        l_jobNumber.setOpaque(false);


        contentPane.add(p_center, BorderLayout.CENTER);

        this.getLayeredPane().setLayout(null);
        this.getLayeredPane().setOpaque(false);
        imagePanel.add(contentPane);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(background.getIconWidth(), background.getIconHeight());
        this.setResizable(true);
        this.setVisible(true);
    }

    private void b_exit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void putItem2Menu(JMenu jMenu, JMenuItem[] jMenuItem) {
        if (jMenu != null && jMenuItem != null && jMenuItem.length > 0) {
            //将菜单子项加入菜单中
            for (int i = 0; i < jMenuItem.length; i++) {
                jMenu.add(jMenuItem[i]);
                if (i < jMenuItem.length - 1) {
                    jMenu.addSeparator();
                }
            }
        }
    }

    private void b_about_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "卖家&店铺平台 制作！");
    }

    /*
     * 加载按钮事件方法
     */
    public void b_loadident_ActionPerformed(ActionEvent e) {
        int k = 0;
        chooseTime = 0;
        //从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取
        BufferedReader reader = null;
        //此方法会返回一个int值
        int i = filechooser.showOpenDialog(this); // 显示打开文件对话框
                /*
                 * 确实所选择的是对话框上的确定按钮
                 */
        if (i == JFileChooser.APPROVE_OPTION) { // 点击对话框中打开选项
            File f = filechooser.getSelectedFile(); // 得到所选择的文件
            filePath = f.getParent();
            System.out.println("filePath:" + filePath);
            try {
                l_information.setText("数据加载中，请稍等...");
                //读取字符流
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
                l_information.setText("文件读取成功");
                //循环读取
                while (true) {
                    //读取一个文本行
                    String data = reader.readLine();
                    l_information.setText(data);
                    if (data == null) {
                        l_information.setText("数据加载完成！");
                        break;
                    }

                    //过滤掉注释
                    if (data.startsWith("#")) {
                        continue;
                    }

                    //加载奖项设定
                    if (data.startsWith("@")){
                        award = new Award(data);
                        continue;
                    }

                    //通过“-”这个符号将 字符串分离为两部分，再存放到向量里面
                    Vector v = this.apart(data, "-");
                    //System.out.println(data);//开发时候测试用
                    if (v == null) {
                        l_information.setText("数据格式不正确，请重新加载！");
                        return;
                    }
                    try {
                        vJobName.add(k, v.elementAt(0));
                        vJobNumber.add(k, v.elementAt(1).toString().trim());
                        k++;
                    } catch (Exception e4) {
                        System.out.println("格式中没有分隔符号出现的错误." + data);
                        l_information.setText("导入的数据格式错误！");
                        break;
                    }

                }
            } catch (Exception ex) {
                ex.printStackTrace(); // 输出出错信息
            }
        }
    }

    /*
     * 启动按钮的事件
     */
    public void b_start_ActionPerformed(ActionEvent e) {
        //判断存储两个标记的向量中是否为空
        if (vJobNumber.size() <= 0 || vJobName.size() <= 0) {
            l_information.setText("数据没有加载,请加载数据!");
        } else {
            if (chooseTime >= award.getAwardCount()) {
                l_information.setText("抽奖结束,若要再进行一次须重新启动程序!");
            } else//执行....
            {
                awardThread = new ChooseThread(this);
                awardThread.changeflag_start();
                l_information.setText(award.getAwardDescription());
//                l_jobNumber.setText("选取中...");
                String awardName = award.getCurrentAwardName(chooseTime + 1);
                l_jobNumber.setText(awardName);
                b_start.setEnabled(false);
                b_stop.setEnabled(true);
                b_rechoose.setEnabled(false);
            }
        }
    }
    public void b_rechoose_ActionPerformed(ActionEvent e) {
        if (chooseTime > 0){
            chooseTime--;
            v_printident.remove(chooseTime);
        }
        b_start_ActionPerformed(e);
    }

    /*
     * 暂停按钮的事件
     */
    public void b_stop_ActionPerformed(ActionEvent e) {
        //将跳转的数字置于停止状态
        awardThread.changeflag_stop();
        chooseTime++;//第几次按停止按钮
        lotteryAward();
        b_start.setEnabled(true);
        b_stop.setEnabled(false);
        b_rechoose.setEnabled(true);
    }

    private void lotteryAward() {
        String str_name = null;
        String message;//寻找停止在号码框中的名字，是对应的的存放号码的向量中的第几个
        String awardName = award.getCurrentAwardName(chooseTime);
        for (int k = 0; k < vJobName.size(); k++) {
            //找到了对应的号码
            if ((jobName.getText()).equals(vJobName.elementAt(k))) {
                //取出这个号码对应的名字
                str_name = (String) vJobName.elementAt(k);
                //为防止下次抽的时候再抽到相同的名字，所以把它们从向量中移除掉
                vJobNumber.removeElementAt(k);
                vJobName.removeElementAt(k);
                break;//跳出循环
            }
        }
        l_jobNumber.setText(awardName + "(" + award.getCurrentAwardCount(chooseTime) + ")");
//        b_start.setText("继续");
        //这是要输出到文本文件的信息
        String awardMessage = awardName + "  " + jobName.getText() + "  " + jobNumber.getText() + "\r\n";
        //将要打印的文本信息先存放到一个可变向量中
        v_printident.addElement(awardMessage);
        message = "第" + chooseTime + "位获奖得主为：" + str_name + "  奖项："  + awardName;
        JOptionPane.showMessageDialog(this, message);
    }

    /*
     * 输出按钮的事件
     */
    public void b_printaward_ActionPerformed(ActionEvent e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH_mm_ss");
        String dateStr = sdf.format(new Date());
        try {
            FileOutputStream fs_out = new FileOutputStream(filePath + File.separator + "award_result" + dateStr + ".txt");
            for (int i = 0; i < v_printident.size(); i++) {
                fs_out.write((v_printident.elementAt(i)).getBytes());
            }
            fs_out.close();
            l_information.setText("文件输出成功！保存在当前目录下..");
        } catch (FileNotFoundException fe) {
            System.err.println(fe);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }

    }

    public Vector apart(String src, String separator) {
        Vector<String> v = new Vector<String>();
        StringTokenizer st = new StringTokenizer(src, separator);
        //测试此 tokenizer 的字符串中是否还有更多的可用标记
        while (st.hasMoreTokens()) {
            //返回此 string tokenizer 的下一个标记,并将它加入到可变向量中存放
            v.addElement(st.nextToken());
        }
        return v;//返回向量
    }

    /*
     * 程序的入口
     */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        ChooseAward award = new ChooseAward();
    }
}