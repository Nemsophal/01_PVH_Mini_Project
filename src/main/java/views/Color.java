package views;

public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\033[34m");
    private final String code;
    Color(String code){
        this.code = code;
    }

    public String code(){
        return code;
    }
}
