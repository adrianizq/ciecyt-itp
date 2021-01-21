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

    private String imagePath;

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Upload getUpload() {
        return this.upload;
    }

    public static class Upload {

        private final Root root = new Root();

        private final Organization organization = new Organization();

        private final Announcement announcement = new Announcement();

        private final User user = new User();

        private final Country country = new Country();

        public Root getRoot() {
            return this.root;
        }

        public Organization getOrganization() {
            return this.organization;
        }

        public Announcement getAnnouncement() {
            return this.announcement;
        }

        public User getUser() {
            return this.user;
        }

        public Country getCountry() {
            return this.country;
        }

        public static class Root extends DirPath {}

        public static class Organization extends DirPath {}

        public static class Announcement extends DirPath {}

        public static class User extends DirPath {}

        public static class Country extends DirPath {}
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
