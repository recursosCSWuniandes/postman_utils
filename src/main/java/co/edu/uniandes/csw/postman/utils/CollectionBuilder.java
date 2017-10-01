/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.postman.utils;

import co.edu.uniandes.csw.postman.interfaces.IPostmanCollections;
import java.io.File;

/**
 *
 * @author Asistente
 */
public class CollectionBuilder implements IPostmanCollections {

    private File fil;
    
    public CollectionBuilder (File f){
    this.fil = f;
    }
    
    @Override
    public boolean validateFile() {
        return getFil().isFile(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOriginalName() {
        return getFil().getName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCollection(String name) {
          name = name.concat(".json");
       return name.equals(getFil().getName()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnvironment(String name) {
        name = name.concat(".json");
       return name.equals(getFil().getName()); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nameMatch(String name) {
       return getFil().getName().contains(name);
         //To change body of generated methods, choose Tools | Templates.
    }
    
      @Override
    public File getFile() {
        return getFil().getAbsoluteFile(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the file
     */
    
    public File getFil() {
        return fil;
    }

    /**
     * @param file the file to set
     */
    public void setFile(File file) {
        this.fil = file;
    }

 
    
    
    
    
}
