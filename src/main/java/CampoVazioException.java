public class CampoVazioException extends Exception{
    public CampoVazioException(String campos){
        super("O(s) campo(s) " + campos + " são obrigatório(s)");
    }
}
