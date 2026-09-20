import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Cifrador {
    public static void archivo(String ruta,boolean cifrar,int corrimiento)throws  IOException{
        File archivo_o=new File(ruta);
        String ruta_d=nombre(ruta,cifrar);
        boolean bmp=ruta.toLowerCase().endsWith(".bmp");
        int t_cabecera=54;
        try(FileInputStream fis=new FileInputStream(archivo_o);
        FileOutputStream fos=new FileOutputStream(ruta_d)){
            int bmp_byte;
            int c=0;
            while((bmp_byte=fis.read())!=-1){
                if(bmp&&c<t_cabecera){
                    fos.write(bmp_byte);
                }
                else{
                    int Cbmp;
                    if(cifrar){
                        Cbmp=(bmp_byte+corrimiento)&0xFF;
                    }
                    else{
                        Cbmp=(bmp_byte-corrimiento)&0xFF;
                    }
                    fos.write(Cbmp);
                }
                c++;
            }
        }
    }

    private static String nombre(String ruta,boolean cifrar)throws IOException{
        int punto=ruta.lastIndexOf('.');
        String sufijo=cifrar?"_E":"_D";
        if (punto==-1){
            return ruta+sufijo;
        }
        else{
            String nombreb=ruta.substring(0,punto);
            String nombresufijo=ruta.substring(punto);
            return nombreb+sufijo+nombresufijo;
        }
    }
}