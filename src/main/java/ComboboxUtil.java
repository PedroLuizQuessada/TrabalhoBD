import javax.swing.*;
import java.sql.*;

public class ComboboxUtil {
    private final DataUtil dataUtil = new DataUtil();

    public void populaCombobox(JComboBox<String> comboBox, String sql, Connection con, boolean data) throws SQLException {
        PreparedStatement pStmtAux = con.prepareStatement(sql);
        ResultSet rs = pStmtAux.executeQuery();

        while (rs.next()){
            if (data){
                Date valor = rs.getDate(1);
                comboBox.addItem(dataUtil.sqlDateParaString(valor));
            }
            else {
                String valor = rs.getString(1);
                comboBox.addItem(valor);
            }
        }
    }
}
