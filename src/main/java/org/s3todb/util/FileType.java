package org.s3todb.util;
import java.util.Arrays;
import java.util.Optional;

public enum FileType {
    JSON(new String[]{Constants.JSON, Constants.JL}),
    CSV(new String[]{Constants.CSV});

    private final String[] extensions;

    FileType(String[] extensions) {
        this.extensions = extensions;
    }

    public String[] getExtensions() {
        return extensions;
    }

    public static String fromFileName(String fileName) {
        if (fileName != null && fileName.contains(".")) {
            String ext = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
            Optional<FileType> fileType = Arrays.stream(values())
                    .filter(type -> Arrays.asList(type.extensions).contains(ext))
                    .findFirst();
            if (fileType.isPresent()) {
                return fileType.get().toString();
            }
        }
        return "";
    }
}


