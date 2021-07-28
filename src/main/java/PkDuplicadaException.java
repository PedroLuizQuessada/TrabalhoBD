public class PkDuplicadaException extends Exception{
    public PkDuplicadaException(String indices){
        super("Já existe um registro para o(s) índice(s) " + indices + " inseridos");
    }
}
