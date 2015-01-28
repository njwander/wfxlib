package wfx.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;

import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.*;

/**
 * 迅雷会员高速通道的被限制后的破解方式，注意有两个变量需要修改：使用方法：
 * 1.先开启迅雷，登录会员，选择要下载的资源后，暂停，关闭迅雷。
 * 2.执行程序。
 * 3.开启迅雷，等待会员登录后，如果出现已经进入高速通道的字样，说明破解成功，否则关闭迅雷，再试一下。
 * Created by fanxin.wfx on 14-12-26.
 */
public class ThunderCrack {
    //迅雷安装目录下的文件路径，文见面是TaskDb.dat
    String sqliteDataPath = "d:\\Program Files (x86)\\Thunder Network\\Thunder\\Profiles\\TaskDb.dat";
    //注意，需要手工找到这个这个表名，不同的用户，表名不一样。可以通过使用sqlite expert软件来查看一下。
//    String thunderUserAccelerateTable = "AccelerateTaskMap347993852_superspeed_1_1";

    public List<String> queryAllAccelerateTable(Statement stat) throws SQLException {
        List<String> tableList = new ArrayList<>();
        ResultSet rs = stat.executeQuery("SELECT name FROM sqlite_master " +
                "WHERE type='table' " +
                "ORDER BY name");
        while (rs.next()) {
            String tableName = rs.getString("name");
            if (tableName.contains("AccelerateTaskMap") && tableName.contains("superspeed")) {
                tableList.add(tableName);
            }
        }
        rs.close();
        return tableList;
    }

    public void loadThunderData() {
        try {
            // 连接SQLite的JDBC
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager
                    .getConnection("jdbc:sqlite:" + sqliteDataPath);
            Statement stat = conn.createStatement();
            List<String> tableList = queryAllAccelerateTable(stat);
            if (CollectionUtils.isNotEmpty(tableList)) {
                for (String tableName : tableList) {
                    updateAccelerateState(conn, stat, tableName);
                }
            }
            stat.close();
            conn.close(); // 结束数据库的连接
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新加速表中的状态
     *
     * @param conn
     * @param stat
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    private void updateAccelerateState(Connection conn, Statement stat, String tableName) throws SQLException, UnsupportedEncodingException {
        System.out.println("in updateAccelerateState:" + tableName);
        ResultSet rs = stat.executeQuery("select * from " + tableName);
        Map<Long, String> updateSqlMap = new HashMap<>();
        //遍历所有的数据，准备生成update语句
        while (rs.next()) {
            byte[] bytes = rs.getBytes("UserData");
            String userDataStr = new String(bytes, "UTF-8");
            JSONObject userDataObject = JSONObject.parseObject(userDataStr);
            //此处的result是关键，是否进入高速通道，实际上是本地判断的逻辑，是先远程请求一次进行校验，校验的结果会保存下来。
            // 0表示已经进入，再次启动下载的时候，就不会进行校验了，直接进入高速通道。
            userDataObject.put("Result", 0);
            userDataStr = userDataObject.toJSONString();
            Long localTaskId = rs.getLong("LocalTaskId");
            updateSqlMap.put(localTaskId, userDataStr);
        }
        rs.close();
        String sql = "update " + tableName + " set UserData=? where LocalTaskId=?";
        for (Map.Entry<Long, String> entry : updateSqlMap.entrySet()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBytes(1, entry.getValue().getBytes("UTF-8"));
            ps.setLong(2, entry.getKey());
            int cnt = ps.executeUpdate();
            if (cnt > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
            ps.close();
        }
    }

    public static void main(String[] args) {
        ThunderCrack crack = new ThunderCrack();
        crack.loadThunderData();
    }
}
