package test_components;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import pages.ShippingForm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DataReader {

    public static ShippingForm readShippingForm(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, ShippingForm.class);
    }
}