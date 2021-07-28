import java.sql.Date;

public class DataUtil {
    public Date stringParaSqlDate(String dataBr){
        String dataFormatada = dataBr.substring(6) + "-" + dataBr.substring(3, 5) + "-" + dataBr.substring(0, 2);
        return Date.valueOf(dataFormatada);
    }

    public String sqlDateParaString(Date data){
        String dataNaoFormatada = data.toString();
        return dataNaoFormatada.substring(8) + "/" + dataNaoFormatada.substring(5, 7) + "/" + dataNaoFormatada.substring(0, 4);
    }
}
