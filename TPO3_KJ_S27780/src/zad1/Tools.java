/**
 *
 *  @author Karwowski Jakub S27780
 *
 */

package zad1;


import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tools {
    public static Options createOptionsFromYaml(String fileName) throws Exception {
        OptionsHehe result=null;
        try(FileInputStream fileInputStream = new FileInputStream(fileName);){
            result = new Yaml().loadAs(fileInputStream, OptionsHehe.class);
            return new Options(result.getHost(),result.getPort(), result.isConcurMode(),result.isShowSendRes(),result.getClientsMap());
        }
    }
}
