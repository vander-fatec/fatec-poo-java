package negocio;

import java.security.MessageDigest;

public class Login{
    private static Usuario usuario;
    private static boolean logado;
    public static final String SENHAPADRAO = "senha@123"; 
    
    public Login(){
        
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        Login.usuario = usuario;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        Login.logado = logado;
    }
    
    public static String getSenhaPadrao(){
        return SENHAPADRAO;
    }
    
    public static String gerarHash(String senha){
        //http://stackoverflow.com/questions/5531455/how-to-hash-some-string-with-sha256-in-java
        //http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(senha.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }        
    }
        
    public static boolean compararHash(String senha_entrada, String senha_bd){
        return (gerarHash(senha_entrada).equals(senha_bd));
    }
    
}
