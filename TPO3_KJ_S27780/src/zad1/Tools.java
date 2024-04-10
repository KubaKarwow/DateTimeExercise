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
        try(FileInputStream fileInputStream = new FileInputStream(fileName);){
            Options load = new Yaml().loadAs(fileInputStream,Options.class);
            return load;
        }
    }
}
