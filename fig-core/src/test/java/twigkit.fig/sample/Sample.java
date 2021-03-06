package twigkit.fig.sample;

import twigkit.fig.Config;
import twigkit.fig.configurable.Configurable;

/**
 * @author mr.olafsson
 */
public abstract class Sample implements Configurable {

    protected Config config;

    public abstract void validate();

    public void configure(Config config) {
        this.config = config;
    }
}
