package services;

public enum FilesPaths {

    TEST_RESOURCES_PATH("src\\test\\resources\\");

    private String filesPath;

    FilesPaths(String filesPath) {
        this.filesPath = filesPath;
    }

    public String getFilesPath() {
        return filesPath;
    }
}
