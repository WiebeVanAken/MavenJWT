package personal.wiebe.jwt.core.providers;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Singleton;
import personal.wiebe.jwt.api.providers.ISecretKeyProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

@Default
@Singleton
final class SecretKeyProvider implements ISecretKeyProvider {
    public String provide() throws IOException {
        return readSecretKeyFileContents();
    }

    private String readSecretKeyFileContents() throws IOException {
        var file = new File("secretkey.properties");

        if (file.createNewFile()) {
            var fileWriter = new FileWriter(file);

            fileWriter.write(java.util.UUID.randomUUID().toString());
            fileWriter.close();
        }

        var scanner = new Scanner(file);
        return scanner.next();
    }
}
