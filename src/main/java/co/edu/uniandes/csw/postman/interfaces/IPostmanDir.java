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
public interface IPostmanDir {
    public boolean validateDir();
    public File[] getFiles();
     public int countFiles();
}
