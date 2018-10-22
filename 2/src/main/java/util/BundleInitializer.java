package util;

import domain.Bundle;
import domain.enumeration.BundleType;

public class BundleInitializer {
    public static Bundle initBundle(BundleType bundleType) {
        Bundle bundle = new Bundle();
        switch(bundleType) {
            case Base:
                return bundle;

            case Call:
                bundle.setFunctionFee(20);
                bundle.setFreeCallLimit(100);
                bundle.setExceedCallFee(0.5);
                return bundle;

            case SMS:
                bundle.setFunctionFee(10);
                bundle.setFreeSMSLimit(200);
                bundle.setExceedSMSFee(0.1);

            case Local:
                bundle.setFunctionFee(20);
                bundle.setLocalDataFee(2000);
                bundle.setExceedSMSFee(3);

            case Domestic:
                bundle.setFunctionFee(30);
                bundle.setFreeDomesticDataLimit(2000);
                bundle.setExceedDomesticDataLimit(3);
        }
        return new Bundle();
    }

}
