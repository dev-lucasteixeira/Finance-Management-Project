package com.lucasteixeira.finance_management.shared.infrastructure;

import com.lucasteixeira.finance_management.shared.domain.FeatureManager;
import io.getunleash.Unleash;
import io.getunleash.UnleashContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnleashFeatureManager implements FeatureManager {
    private final Unleash unleash;

    @Override
    public boolean isEnabled(String featureName, String userId) {
        UnleashContext context = UnleashContext.builder()
                .userId(userId)
                .build();
        return unleash.isEnabled(featureName, context);
    }
}
