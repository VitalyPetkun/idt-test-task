package services;

public enum FilesNames {

    TEST_DATA("testData.properties");

    private String fileName;

    FilesNames(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
