package com.lucasteixeira.finance_management.shared.domain;

public interface FeatureManager {
    boolean isEnabled(String featureName, String userId);
}
