package com.integralads.avid.library.mopub.processing;

public class AvidProcessorFactory {
    private AvidSceenProcessor screenProcessor;
    private AvidViewProcessor viewProcessor;

    public AvidProcessorFactory() {
        AvidViewProcessor avidViewProcessor = new AvidViewProcessor();
        this.viewProcessor = avidViewProcessor;
        this.screenProcessor = new AvidSceenProcessor(avidViewProcessor);
    }

    public IAvidNodeProcessor getRootProcessor() {
        return this.screenProcessor;
    }
}
