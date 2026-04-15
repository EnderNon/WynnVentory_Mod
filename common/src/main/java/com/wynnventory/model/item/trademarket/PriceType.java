package com.wynnventory.model.item.trademarket;

import com.wynnventory.core.config.settings.TooltipSettings;
import java.util.function.Function;
import java.util.function.Predicate;

public enum PriceType {
    AVG_80(
            "feature.wynnventory.tooltip.avg80",
            TooltipSettings::isShowAverage80Price,
            TrademarketItemSummary::getAverageMid80PercentPrice),
    UNID_AVG_80(
            "feature.wynnventory.tooltip.unidAvg80",
            TooltipSettings::isShowUnidAverage80Price,
            TrademarketItemSummary::getUnidentifiedAverageMid80PercentPrice),
    AVG(
            "feature.wynnventory.tooltip.avg",
            TooltipSettings::isShowAveragePrice,
            TrademarketItemSummary::getAveragePrice),
    UNID_AVG(
            "feature.wynnventory.tooltip.unidAvg",
            TooltipSettings::isShowUnidAveragePrice,
            TrademarketItemSummary::getUnidentifiedAveragePrice),
    HIGHEST(
            "feature.wynnventory.tooltip.highest",
            TooltipSettings::isShowMaxPrice,
            s -> s.getHighestPrice() == null ? null : (double) s.getHighestPrice()),
    UNID_HIGHEST(
            "feature.wynnventory.tooltip.unidHighest",
            TooltipSettings::isShowUnidentifiedMaxPrice,
            s -> s.getUnidentifiedHighestPrice() == null ? null : (double) s.getUnidentifiedHighestPrice()),
    LOWEST(
            "feature.wynnventory.tooltip.lowest",
            TooltipSettings::isShowMinPrice,
            s -> s.getLowestPrice() == null ? null : (double) s.getLowestPrice()),
    UNID_LOWEST(
            "feature.wynnventory.tooltip.unidLowest",
            TooltipSettings::isShowUnidentifiedMinPrice,
            s -> s.getUnidentifiedLowestPrice() == null ? null : (double) s.getUnidentifiedLowestPrice()),
    P50(
            "feature.wynnventory.tooltip.p50",
            TooltipSettings::isShowP50,
            s -> s.getP50() == null ? null : (double) s.getP50()),
    P50_EMA(
            "feature.wynnventory.tooltip.p50EMA",
            TooltipSettings::isShowP50EMA,
            s -> s.getP50EMA() == null ? null : (double) s.getP50EMA()),
    UNID_P50(
            "feature.wynnventory.tooltip.unidP50",
            TooltipSettings::isShowUnidP50,
            s -> s.getUnidentifiedP50() == null ? null : (double) s.getUnidentifiedP50()),
    UNID_P50_EMA(
            "feature.wynnventory.tooltip.unidP50EMA",
            TooltipSettings::isShowUnidP50EMA,
            s -> s.getUnidentifiedP50EMA() == null ? null : (double) s.getUnidentifiedP50EMA());

    private final String label;
    private final Predicate<TooltipSettings> enabledCheck;
    private final Function<TrademarketItemSummary, Double> extractor;

    PriceType(
            String label, Predicate<TooltipSettings> enabledCheck, Function<TrademarketItemSummary, Double> extractor) {
        this.label = label;
        this.enabledCheck = enabledCheck;
        this.extractor = extractor;
    }

    public String getLabel() {
        return label;
    }

    public boolean isEnabled(TooltipSettings settings) {
        return enabledCheck.test(settings);
    }

    public Double getValue(TrademarketItemSummary summary) {
        return (summary == null) ? null : extractor.apply(summary);
    }
}
