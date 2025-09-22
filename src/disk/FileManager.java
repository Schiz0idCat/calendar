package disk;

import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class FileManager<T> {
    public enum FileType {
        CONFIG, DATA
    }

    protected final Path filePath;

    public FileManager(String appName, String fileName, FileType fileType) throws IOException {
        this.filePath = getDefaultPath(appName, fileName, fileType);
        createFileIfNotExists();
    }

    private static Path getDefaultPath(String appName, String fileName, FileType fileType) throws IOException {
        AppDirs appDirs = AppDirsFactory.getInstance();
        String baseDir;

        switch (fileType) {
            case CONFIG:
                baseDir = appDirs.getUserConfigDir(appName, null, null);
                break;
            case DATA:
                baseDir = appDirs.getUserDataDir(appName, null, null);
                break;
            default:
                throw new IllegalArgumentException("Unknown file type: " + fileType);
        }

        Path path = Path.of(baseDir, fileName);

        if (!Files.exists(path.getParent())) {
            Files.createDirectories(path.getParent());
        }

        return path;
    }

    private void createFileIfNotExists() throws IOException {
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public abstract void save(T data);

    public abstract T load();

    public abstract void export(T data) throws IOException;
}
