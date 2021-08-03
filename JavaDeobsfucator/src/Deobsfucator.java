import com.javadeobfuscator.deobfuscator.Deobfuscator;
import com.javadeobfuscator.deobfuscator.config.Configuration;
import com.javadeobfuscator.deobfuscator.config.TransformerConfig;
import com.javadeobfuscator.deobfuscator.transformers.general.peephole.PeepholeOptimizer;

import java.io.File;
import java.util.Arrays;

public class Deobsfucator {

    public static void main(String[] args) throws Throwable {
        Configuration config = new Configuration();
        config.setInput(new File("C:\\Users\\josep\\IntelliJProjects\\JavaDeobsfucator\\SkyblockExtras-2.1.4.1.jar"));
        config.setOutput(new File("output.jar"));
        config.setPath(Arrays.asList(
                new File("C:\\Program Files\\Java\\jre1.8.0_301\\lib\\rt.jar"),
                new File("C:\\Program Files\\Java\\jre1.8.0_301\\jre\\lib\\jce.jar"),
                new File("C:\\Program Files\\Java\\jre1.8.0_301\\jre\\lib\\ext\\jfxrt.jar"),
                new File("C:\\Program Files\\Java\\jre1.8.0_301\\lib\\tools.jar")
        ));
        config.setTransformers(Arrays.asList(
                TransformerConfig.configFor(PeepholeOptimizer.class)
        ));
        new Deobfuscator(config).start();
    }

}
