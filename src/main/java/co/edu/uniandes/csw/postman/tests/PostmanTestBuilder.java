/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.postman.tests;

import co.edu.uniandes.csw.postman.utils.CollectionBuilder;
import co.edu.uniandes.csw.postman.utils.PathBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Asistente
 */
public class PostmanTestBuilder {

    private String env;
    private String coll;
    private PathBuilder path;
    private CollectionBuilder cb;
    private Process process;
    private InputStream inputStream;
    private BufferedReader bf;
    private String line;
    private String ln;
    private String requests_failed;
    private String iterations_failed;
    private String assertions_failed;
    private String test_scripts_failed;
    private String prerequest_scripts_failed;
    private String total_requests;
    private File tmp;
    private BufferedWriter bw;

    public PostmanTestBuilder() {
        path = new PathBuilder();
        env = " -e ";
        coll = "newman run ";
        line = "";
        ln = null;
        prerequest_scripts_failed = null;
        test_scripts_failed = null;
        assertions_failed = null;
        iterations_failed = null;
        requests_failed = null;
        total_requests = null;
    }

    public void setTestWithoutLogin(String collectionName) throws IOException {

        if (path.validateDir()) {
            for (File f : path.getFiles()) {
                cb = new CollectionBuilder(f);
                if (cb.isCollection(collectionName)) {
                    coll = coll.concat(path.getPATH().concat("\\").concat(cb.getOriginalName()));
                }
            }
        }
        tmp = File.createTempFile(collectionName, ".bat");
        bw = new BufferedWriter(new FileWriter(tmp));
        bw.write(coll.concat(" --disable-unicode"));
        bw.close();
        tmp.setExecutable(true);
        startProcess();
    }

    public void setTestWithoutLogin(String collectionName, String environmentName) throws IOException {

        if (path.validateDir()) {
            for (File f : path.getFiles()) {
                cb = new CollectionBuilder(f);

                if (cb.isEnvironment(environmentName)) {
                    env = env.concat("\"" + path.getPATH().concat("\\").concat(cb.getOriginalName() + "\"" + " --disable-unicode"));
                }
                if (cb.isCollection(collectionName)) {
                    coll = coll.concat("\"" + path.getPATH().concat("\\").concat(cb.getOriginalName()) + "\"");
                }
            }
        }
        tmp = File.createTempFile(collectionName, ".bat");

        bw = new BufferedWriter(new FileWriter(tmp));
        bw.write(coll.concat(env));
        bw.close();
        tmp.setExecutable(true);
        startProcess();
    }

    private void startProcess() {

        try {
            process = Runtime.getRuntime().exec(tmp.getAbsolutePath());
            inputStream = process.getInputStream();
            bf = new BufferedReader(new InputStreamReader(inputStream));

            while ((ln = bf.readLine()) != null) {
                line = line.concat(ln + "\n");

                if (ln.contains("requests")) {
                    total_requests = ln.substring(27, 37).trim();
                    if (!"0".equals(total_requests)) {
                        requests_failed = ln.substring(38, 48).trim();
                    }
                }
                if (ln.contains("assertions")) {
                    if (!"0".equals(ln.substring(27, 37).trim())) {
                        assertions_failed = ln.substring(38, 48).trim();
                    }
                }
                if (ln.contains("iterations")) {
                    if (!"0".equals(ln.substring(27, 37).trim())) {
                        iterations_failed = ln.substring(38, 48).trim();
                    }
                }
                if (ln.contains("test-scripts")) {
                    if (!"0".equals(ln.substring(27, 37).trim())) {
                        test_scripts_failed = ln.substring(38, 48).trim();
                    }
                }
                if (ln.contains("prerequest-scripts")) {
                    if (!"0".equals(ln.substring(27, 37).trim())) {
                        prerequest_scripts_failed = ln.substring(38, 48).trim();
                    }
                }
            }

            System.out.println(line);
            inputStream.close();
            bf.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        tmp.deleteOnExit();
    }

    /**
     * @return the requests_failed
     */
    public String getRequests_failed() {
        return requests_failed;
    }

    /**
     * @return the iterations_failed
     */
    public String getIterations_failed() {
        return iterations_failed;
    }

    /**
     * @return the assertions_failed
     */
    public String getAssertions_failed() {
        return assertions_failed;
    }

    /**
     * @return the test_scripts_failed
     */
    public String getTest_scripts_failed() {
        return test_scripts_failed;
    }

    /**
     * @return the prerequest_scripts_failed
     */
    public String getPrerequest_scripts_failed() {
        return prerequest_scripts_failed;
    }

    /**
     * @return the total_requests
     */
    public String getTotal_Requests() {
        return total_requests;
    }
}
