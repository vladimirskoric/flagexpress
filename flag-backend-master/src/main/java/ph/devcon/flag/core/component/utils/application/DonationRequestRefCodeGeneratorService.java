package ph.devcon.flag.core.component.utils.application;

import ph.devcon.flag.core.component.donationrequest.domain.DonationRequest;
import ph.devcon.flag.core.component.exception.RefCodeGenerationException;
import ph.devcon.flag.core.port.persistence.DonationRequestRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class DonationRequestRefCodeGeneratorService implements RefCodeGeneratorService<DonationRequest> {

    private final int VALUE_MIN = 100_000;
    private final int VALUE_MAX = 999_999;
    private final int TRIAL_MAX = 10;

    @Inject
    DonationRequestRepository donationRequestRepository;

    /**
     * This generates a number in the range of 100000 to 999999, which will be used as the unique external reference code.
     * @return A string containing 6 digits.
     */
    @Override
    public String generateRefCode() {
        String refCodeCandidate;
        DonationRequest existingItem;

        int trialCounter = 0;
        do {
            refCodeCandidate = String.valueOf(ThreadLocalRandom.current().nextInt(VALUE_MIN, VALUE_MAX + 1));
            existingItem = donationRequestRepository.findDonationRequestByRefCode(refCodeCandidate);
            trialCounter++;
        } while (existingItem != null && trialCounter < TRIAL_MAX);

        if (existingItem != null && trialCounter == TRIAL_MAX) {
            throw new RefCodeGenerationException("Cannot generate reference code.");
        }

        return refCodeCandidate;
    }

}