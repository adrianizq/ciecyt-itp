package co.edu.itp.ciecyt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Ciecyt.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Upload upload = new Upload();

    private String filesPath;

    public String getFilesPath() {
        return this.filesPath;
    }

    public void setFilesPath(String imagePath) {
        this.filesPath = imagePath;
    }

    public Upload getUpload() {
        return this.upload;
    }

    public static class Upload {

        private final Root root = new Root();

        private final Files files = new Files();

        

        public Root getRoot() {
            return this.root;
        }

        public Files getFiles() {
            return this.files;
        }

       

        public static class Root extends DirPath {}

        public static class Files extends DirPath {}

        
    }

    public static class DirPath {

        private String dir;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }
}
