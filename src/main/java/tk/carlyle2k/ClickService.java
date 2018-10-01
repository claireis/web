package tk.carlyle2k;

import java.sql.*;

import static tk.carlyle2k.ConnectionService.getConn;

public class ClickService {
    /**
     * sql
     */
    private static final String GET_SQL = "select id, val from click limit 1";
    private static final String UPDATE_SQL = "update click set val = ? where id = ?";

    private static final int ERROR_CLICK = -1;

    /**
     * increase click val then get the value
     * @return
     */
    public int incrementAndGet() {
        try (Connection conn = getConn()) {

            PreparedStatement psGet = conn.prepareStatement(GET_SQL);
            ResultSet rs = psGet.executeQuery();

            //if find, first increase then return
            if (rs.next()) {
                int id = rs.getInt("id");
                int val = rs.getInt("val");

                //check update
                PreparedStatement psUpdate = conn.prepareStatement(UPDATE_SQL);
                psUpdate.setInt(1, ++val);
                psUpdate.setInt(2, id);

                if (psUpdate.executeUpdate() == 1) {
                    return val;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ERROR_CLICK;
    }

}
