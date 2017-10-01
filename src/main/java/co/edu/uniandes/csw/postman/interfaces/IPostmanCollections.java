/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.postman.interfaces;

import java.io.File;

/**
 *
 * @author Asistente
 */
public interface IPostmanCollections {
   public String getOriginalName();
   public boolean isCollection(String name);
   public boolean isEnvironment(String name);
   public boolean nameMatch(String name);
 
    
    public File getFile();
   
    public boolean validateFile();
   
}
