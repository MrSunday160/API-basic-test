package com.example.jsonplaceholderapitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ApiService{

    private static final String base_url = "https://jsonplaceholder.typicode.com";
    private static final String endpoint = "/posts";

    public static String getPost(){

        try{

            // define API url, this case we seperate the base and endpoint
            URL url = new URL(base_url + endpoint);

            // open connection to the url
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // set request method to GET
            connection.setRequestMethod("GET");

            // get response code
            int responseCode = connection.getResponseCode();

            // if response code == 200 (OK), successful HTTP GET request
            if(responseCode == HttpURLConnection.HTTP_OK){

                // read the response data
                // this bit sets up a BufferedReader to read the input stream of the HTTP response
                // creates new BufferedReader instance and wraps it around the input stream obtained from connection.getInputStream()
                // This input stream is where the response data from the server is received
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // initialize StringBuilder to build a string that will hold the entire HTTP response
                // we use StringBuilder here because it's more efficient when concatenating small string fragments
                // useful when reading data in chunks from the input stream
                StringBuilder response = new StringBuilder();

                String line;

                // iterates through each line of the response data received from the server using reader.readLine()
                // reads each line and appends it to the 'response' StringBuilder
                // loops until no more lines to read
                while((line = reader.readLine()) != null)
                    response.append(line);

                // after the loop is done, the 'response' StringBuilder will contain the entire HTTP response from the server
                // as a single string
                // can be processed further such as parsing it as JSON and display it in the UI

                // close reader and connection
                reader.close();
                connection.disconnect();

                // return the JSON response as a string
                return response.toString();

            }

            else // error case here
                return null;

        }

        catch(IOException e){

            e.printStackTrace();
            return "Error: Unable to retrieve data - Network Error";

        }

        catch (Exception ex) {
            ex.printStackTrace();
            return "Error: Something went wrong";
        }

    }

}
