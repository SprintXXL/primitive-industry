package com.SprintXXL.primitiveindustry.util;

import com.SprintXXL.primitiveindustry.factory.Factory;
import com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry;
import com.SprintXXL.primitiveindustry.industryblocks.IndustryBlock;
import com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.SprintXXL.primitiveindustry.factory.registry.FactoryRegistry.initFactoryRegistry;
import static com.SprintXXL.primitiveindustry.industryblocks.registry.IndustryBlockRegistry.initIndustryBlockRegistry;

public class ResourceFileGenerator {

    static void main() {

        initIndustryBlockRegistry();
        initFactoryRegistry();

        for (IndustryBlock industryBlock : IndustryBlockRegistry.getAllIndustryBlocks()) {
            generateIndustryBlock(industryBlock);
        }

        for (Factory factory : FactoryRegistry.getAllFactories()) {
            generateFactoryController(factory);
        }

        generateLang();
    }

    private static void generateIndustryBlock(IndustryBlock industryBlock) {

        generateCubeBlockModel(
                industryBlock.getID(),
                "primitiveindustry:industryblocks/" + industryBlock.getID()
        );

        generateNormalBlockState(industryBlock.getID());
        generateItemBlock(industryBlock.getID(), industryBlock.getID());
    }

    private static void generateFactoryController(Factory factory) {

        String controller = factory.getControllerName();

        generateCubeBlockModel(
                controller + "_off",
                "primitiveindustry:factories/" + controller + "_off"
        );

        generateCubeBlockModel(
                controller + "_on",
                "primitiveindustry:factories/" + controller + "_on"
        );

        generateActiveBlockState(controller);
        generateItemBlock(controller, controller + "_off");

    }

    private static void generateCubeBlockModel(String modelName, String texturePath) {

        Path path = Paths.get(
                "src/main/resources/assets/primitiveindustry/models/block",
                modelName + ".json"
        );

        String json =
                "{\n" +
                        "  \"parent\": \"block/cube_all\",\n" +
                        "  \"textures\": {\n" +
                        "    \"all\": \"" + texturePath + "\"\n" +
                        "  }\n" +
                        "}";

        writeFile(path, json);
    }

    private static void generateNormalBlockState(String blockName) {
        Path path = Paths.get(
                "src/main/resources/assets/primitiveindustry/blockstates",
                blockName + ".json"
        );

        String json =
                "{\n" +
                        "  \"variants\": {\n" +
                        "    \"normal\": {\n" +
                        "      \"model\": \"primitiveindustry:" + blockName + "\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n";

        writeFile(path, json);
    }

    private static void generateActiveBlockState(String blockName) {
        Path path = Paths.get(
                "src/main/resources/assets/primitiveindustry/blockstates",
                blockName + ".json"
        );

        String json =
                "{\n" +
                        "  \"variants\": {\n" +
                        "    \"active=false\": {\n" +
                        "      \"model\": \"primitiveindustry:" + blockName + "_off\"\n" +
                        "    },\n" +
                        "    \"active=true\": {\n" +
                        "      \"model\": \"primitiveindustry:" + blockName + "_on\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n";

        writeFile(path, json);
    }

    private static void generateItemBlock(String itemName, String parentModel) {
        Path path = Paths.get(
                "src/main/resources/assets/primitiveindustry/models/item",
                itemName + ".json"
        );

        String json =
                "{\n" +
                        "  \"parent\": \"primitiveindustry:block/" + parentModel + "\"\n" +
                        "}\n";

        writeFile(path, json);
    }

    private static void generateLang() {

        Path path = Paths.get(
                "src/main/resources/assets/primitiveindustry/lang",
                "en_us.lang"
        );

        StringBuilder lang = new StringBuilder();

        lang.append("// Industry Blocks \\\\\n");

        for (IndustryBlock industryBlock : IndustryBlockRegistry.getAllIndustryBlocks()) {
            appendBlockLang(lang, industryBlock.getID());
        }

        lang.append("\n// Factory Controllers \\\\\n");

        for (Factory factory : FactoryRegistry.getAllFactories()) {
            appendBlockLang(lang, factory.getControllerName());
        }

        writeFile(path, lang.toString());
    }

    private static void appendBlockLang(StringBuilder lang, String id) {

        lang.append("tile.primitiveindustry.")
                .append(id)
                .append(".name=")
                .append(toDisplayName(id))
                .append("\n");
    }


    private static String toDisplayName(String registryName) {

        String[] parts = registryName.split("_");

        StringBuilder name = new StringBuilder();

        for (String part : parts) {
            if (part.isEmpty()) {
                continue;
            }

            name.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1))
                    .append(" ");
        }

        return name.toString().trim();
    }

    private static void writeFile(Path path, String contents) {

        try {
            Files.createDirectories(path.getParent());

            Files.write(
                    path,
                    contents.getBytes(StandardCharsets.UTF_8)
            );

            System.out.println("Generated: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
