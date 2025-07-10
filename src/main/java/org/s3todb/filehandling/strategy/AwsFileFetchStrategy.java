package org.s3todb.filehandling.strategy;

import org.s3todb.filehandling.cloud.Cloud;
import org.s3todb.util.Constants;
import org.s3todb.util.LogMessages;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AwsFileFetchStrategy implements FileFetchStrategy {
        private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

        private final S3Client s3Client;
        private Cloud awsConfig;
        public AwsFileFetchStrategy(S3Client s3Client, Cloud awsConfig) {
            this.s3Client = s3Client;
            this.awsConfig = awsConfig;
        }

        public String getFilePath(){
            LocalDate date = LocalDate.now();
            return awsConfig.getBucket() + "/" +
                    awsConfig.getPrefix() + "/" +
                    date.getYear() + "/" +
                    date.getMonth() + "/" +
                    date.getDayOfMonth();

        }


        public boolean bucketExist(){
            try {
                s3Client.headBucket(request -> request.bucket(awsConfig.getBucket()));
                return true;
            }
            catch (NoSuchBucketException exception) {
                return false;
            }
        }


        @Override
        public List<String> fetchFileList() {
            List<String> filesList = new ArrayList<>();

            //TODO: Remove once S3 creds are available
            // demo code as S3 connection is not available to test this functionality
            if(this.s3Client == null){
                // get file from resources "resources/JsonFiles"
                return getFileNamesFromResources();
            }

            if(bucketExist()){
                ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                        .bucket(getFilePath())
                        .maxKeys(awsConfig.getPageSize()) // Set the maxKeys parameter to control the page size
                        .build();

                ListObjectsV2Iterable listObjectsV2Iterable = s3Client.listObjectsV2Paginator(listObjectsV2Request);

                for (ListObjectsV2Response page : listObjectsV2Iterable) {
                    // getting Files from s3 as per page size
                    List<S3Object> contents = page.contents();
                    filesList= new ArrayList<>();
                    for(S3Object file : contents){
                        filesList.add(file.key());
                    }
                    return filesList;
                }

            }else{
                LOGGER.log(Level.SEVERE,"S3 Bucket Not available");
            }
            return  filesList;
        }

        @Override
        public BufferedReader fetchFile(String key) {

            //TODO: Remove once S3 creds are available
            // demo code as S3 connection is not available to test this functionality
            if(this.s3Client == null){
                return readFileFromLocal(key);
            }

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(awsConfig.getBucket())
                    .key(key)
                    .build();

            return new BufferedReader(new InputStreamReader(s3Client.getObject(getObjectRequest)));

        }

        //TODO : For testing purpose, fetching all files from local Resource folder
        private List<String> getFileNamesFromResources(){
            List<String> fileNames = new ArrayList<>();
            try {
                String folder = Constants.LOCAL_TESTING_FILES_DIR;
                File directory = new File(folder);
                if (directory.isDirectory()) {
                    File[] files = directory.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            fileNames.add(folder + "/" + file.getName());
                        }
                    }
                }

            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, LogMessages.COULD_NOT_FETCH_FILES);
            }

            return fileNames;
        }

        //TODO : For testing purpose, reading files from local Resource folder
        private BufferedReader readFileFromLocal(String key){
            try {
                // Create a FileReader to read the file
                FileReader fileReader = new FileReader(key);

                // Wrap the FileReader in a BufferedReader to read line by line
                return new BufferedReader(fileReader);
            } catch (IOException e) {
                // Handle the exception (e.g., file not found, I/O error)
                throw new RuntimeException(e.getMessage(), e);
            }
        }




    }


