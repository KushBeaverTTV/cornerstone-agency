package androidx.window.embedding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import androidx.window.R;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: RuleParser.kt */
/* JADX INFO: loaded from: classes14.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J'\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J$\u0010\u001d\u001a\u00020\u001e*\u0012\u0012\u0004\u0012\u00020\u00130\u001fj\b\u0012\u0004\u0012\u00020\u0013` 2\u0006\u0010!\u001a\u00020\u0013H\u0002¨\u0006\""}, d2 = {"Landroidx/window/embedding/RuleParser;", "", "()V", "buildClassName", "Landroid/content/ComponentName;", "pkg", "", "clsSeq", "", "parseActivityFilter", "Landroidx/window/embedding/ActivityFilter;", "context", "Landroid/content/Context;", "parser", "Landroid/content/res/XmlResourceParser;", "parseActivityRule", "Landroidx/window/embedding/ActivityRule;", "parseRules", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRuleResourceId", "", "parseRules$window_release", "parseSplitPairFilter", "Landroidx/window/embedding/SplitPairFilter;", "parseSplitPairRule", "Landroidx/window/embedding/SplitPairRule;", "parseSplitPlaceholderRule", "Landroidx/window/embedding/SplitPlaceholderRule;", "addRuleWithDuplicatedTagCheck", "", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "rule", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class RuleParser {
    public static final RuleParser INSTANCE = new RuleParser();

    private RuleParser() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final Set<EmbeddingRule> parseRules$window_release(Context context, int staticRuleResourceId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Resources resources = context.getResources();
        try {
            XmlResourceParser parser = resources.getXml(staticRuleResourceId);
            Intrinsics.checkNotNullExpressionValue(parser, "resources.getXml(staticRuleResourceId)");
            HashSet<EmbeddingRule> hashSet = new HashSet<>();
            int depth = parser.getDepth();
            int type = parser.next();
            SplitPairRule lastSplitPairRule = null;
            SplitPlaceholderRule lastSplitPlaceholderRule = null;
            ActivityRule lastActivityRule = null;
            while (type != 1 && (type != 3 || parser.getDepth() > depth)) {
                if (parser.getEventType() != 2 || Intrinsics.areEqual("split-config", parser.getName())) {
                    type = parser.next();
                } else {
                    String name = parser.getName();
                    if (name != null) {
                        switch (name.hashCode()) {
                            case 511422343:
                                if (name.equals("ActivityFilter")) {
                                    if (lastActivityRule == null && lastSplitPlaceholderRule == null) {
                                        throw new IllegalArgumentException("Found orphaned ActivityFilter");
                                    }
                                    ActivityFilter activityFilter = parseActivityFilter(context, parser);
                                    if (lastActivityRule != null) {
                                        hashSet.remove(lastActivityRule);
                                        lastActivityRule = lastActivityRule.plus$window_release(activityFilter);
                                        addRuleWithDuplicatedTagCheck(hashSet, lastActivityRule);
                                    } else if (lastSplitPlaceholderRule != null) {
                                        hashSet.remove(lastSplitPlaceholderRule);
                                        lastSplitPlaceholderRule = lastSplitPlaceholderRule.plus$window_release(activityFilter);
                                        addRuleWithDuplicatedTagCheck(hashSet, lastSplitPlaceholderRule);
                                    }
                                }
                                break;
                            case 520447504:
                                if (name.equals("SplitPairRule")) {
                                    SplitPairRule splitConfig = parseSplitPairRule(context, parser);
                                    lastSplitPairRule = splitConfig;
                                    addRuleWithDuplicatedTagCheck(hashSet, lastSplitPairRule);
                                    lastSplitPlaceholderRule = null;
                                    lastActivityRule = null;
                                }
                                break;
                            case 1579230604:
                                if (name.equals("SplitPairFilter")) {
                                    if (lastSplitPairRule == null) {
                                        throw new IllegalArgumentException("Found orphaned SplitPairFilter outside of SplitPairRule");
                                    }
                                    SplitPairFilter splitFilter = parseSplitPairFilter(context, parser);
                                    hashSet.remove(lastSplitPairRule);
                                    lastSplitPairRule = lastSplitPairRule.plus$window_release(splitFilter);
                                    addRuleWithDuplicatedTagCheck(hashSet, lastSplitPairRule);
                                }
                                break;
                            case 1793077963:
                                if (name.equals("ActivityRule")) {
                                    ActivityRule activityConfig = parseActivityRule(context, parser);
                                    addRuleWithDuplicatedTagCheck(hashSet, activityConfig);
                                    lastSplitPairRule = null;
                                    lastSplitPlaceholderRule = null;
                                    lastActivityRule = activityConfig;
                                }
                                break;
                            case 2050988213:
                                if (name.equals("SplitPlaceholderRule")) {
                                    SplitPlaceholderRule placeholderConfig = parseSplitPlaceholderRule(context, parser);
                                    lastSplitPlaceholderRule = placeholderConfig;
                                    addRuleWithDuplicatedTagCheck(hashSet, lastSplitPlaceholderRule);
                                    lastActivityRule = null;
                                    lastSplitPairRule = null;
                                }
                                break;
                        }
                    }
                    type = parser.next();
                }
            }
            return hashSet;
        } catch (Resources.NotFoundException e) {
            return null;
        }
    }

    private final void addRuleWithDuplicatedTagCheck(HashSet<EmbeddingRule> hashSet, EmbeddingRule rule) {
        String tag = rule.getTag();
        HashSet<EmbeddingRule> $this$forEach$iv = hashSet;
        for (Object element$iv : $this$forEach$iv) {
            EmbeddingRule addedRule = (EmbeddingRule) element$iv;
            if (tag != null && Intrinsics.areEqual(tag, addedRule.getTag())) {
                throw new IllegalArgumentException("Duplicated tag: " + tag + " for " + rule + ". The tag must be unique in XML rule definition.");
            }
        }
        hashSet.add(rule);
    }

    private final SplitPairRule parseSplitPairRule(Context context, XmlResourceParser parser) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPairRule, 0, 0);
        String tag = typedArray.getString(R.styleable.SplitPairRule_tag);
        float ratio = typedArray.getFloat(R.styleable.SplitPairRule_splitRatio, 0.5f);
        int minWidthDp = typedArray.getInteger(R.styleable.SplitPairRule_splitMinWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int minHeightDp = typedArray.getInteger(R.styleable.SplitPairRule_splitMinHeightDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int minSmallestWidthDp = typedArray.getInteger(R.styleable.SplitPairRule_splitMinSmallestWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        float maxAspectRatioInPortrait = typedArray.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue());
        float maxAspectRatioInLandscape = typedArray.getFloat(R.styleable.SplitPairRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue());
        int layoutDir = typedArray.getInt(R.styleable.SplitPairRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue());
        int finishPrimaryWithSecondary = typedArray.getInt(R.styleable.SplitPairRule_finishPrimaryWithSecondary, SplitRule.FinishBehavior.NEVER.getValue());
        int finishSecondaryWithPrimary = typedArray.getInt(R.styleable.SplitPairRule_finishSecondaryWithPrimary, SplitRule.FinishBehavior.ALWAYS.getValue());
        boolean clearTop = typedArray.getBoolean(R.styleable.SplitPairRule_clearTop, false);
        SplitAttributes defaultAttrs = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(ratio)).setLayoutDirection(SplitAttributes.LayoutDirection.INSTANCE.getLayoutDirectionFromValue$window_release(layoutDir)).build();
        return new SplitPairRule.Builder(SetsKt.emptySet()).setTag(tag).setMinWidthDp(minWidthDp).setMinHeightDp(minHeightDp).setMinSmallestWidthDp(minSmallestWidthDp).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(maxAspectRatioInPortrait)).setMaxAspectRatioInLandscape(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(maxAspectRatioInLandscape)).setFinishPrimaryWithSecondary(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(finishPrimaryWithSecondary)).setFinishSecondaryWithPrimary(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(finishSecondaryWithPrimary)).setClearTop(clearTop).setDefaultSplitAttributes(defaultAttrs).build();
    }

    private final SplitPlaceholderRule parseSplitPlaceholderRule(Context context, XmlResourceParser parser) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPlaceholderRule, 0, 0);
        String tag = typedArray.getString(R.styleable.SplitPlaceholderRule_tag);
        String placeholderActivityIntentName = typedArray.getString(R.styleable.SplitPlaceholderRule_placeholderActivityName);
        boolean stickyPlaceholder = typedArray.getBoolean(R.styleable.SplitPlaceholderRule_stickyPlaceholder, false);
        int finishPrimaryWithPlaceholder = typedArray.getInt(R.styleable.SplitPlaceholderRule_finishPrimaryWithPlaceholder, SplitRule.FinishBehavior.ALWAYS.getValue());
        if (finishPrimaryWithPlaceholder == SplitRule.FinishBehavior.NEVER.getValue()) {
            throw new IllegalArgumentException("Never is not a valid configuration for Placeholder activities. Please use FINISH_ALWAYS or FINISH_ADJACENT instead or refer to the current API");
        }
        float ratio = typedArray.getFloat(R.styleable.SplitPlaceholderRule_splitRatio, 0.5f);
        int minWidthDp = typedArray.getInteger(R.styleable.SplitPlaceholderRule_splitMinWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int minHeightDp = typedArray.getInteger(R.styleable.SplitPlaceholderRule_splitMinHeightDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        int minSmallestWidthDp = typedArray.getInteger(R.styleable.SplitPlaceholderRule_splitMinSmallestWidthDp, SplitRule.SPLIT_MIN_DIMENSION_DP_DEFAULT);
        float maxAspectRatioInPortrait = typedArray.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInPortrait, SplitRule.SPLIT_MAX_ASPECT_RATIO_PORTRAIT_DEFAULT.getValue());
        float maxAspectRatioInLandscape = typedArray.getFloat(R.styleable.SplitPlaceholderRule_splitMaxAspectRatioInLandscape, SplitRule.SPLIT_MAX_ASPECT_RATIO_LANDSCAPE_DEFAULT.getValue());
        int layoutDir = typedArray.getInt(R.styleable.SplitPlaceholderRule_splitLayoutDirection, SplitAttributes.LayoutDirection.LOCALE.getValue());
        SplitAttributes defaultAttrs = new SplitAttributes.Builder().setSplitType(SplitAttributes.SplitType.INSTANCE.buildSplitTypeFromValue$window_release(ratio)).setLayoutDirection(SplitAttributes.LayoutDirection.INSTANCE.getLayoutDirectionFromValue$window_release(layoutDir)).build();
        String packageName = context.getApplicationContext().getPackageName();
        RuleParser ruleParser = INSTANCE;
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        ComponentName placeholderActivityClassName = ruleParser.buildClassName(packageName, placeholderActivityIntentName);
        Set setEmptySet = SetsKt.emptySet();
        Intent component = new Intent().setComponent(placeholderActivityClassName);
        Intrinsics.checkNotNullExpressionValue(component, "Intent().setComponent(pl…eholderActivityClassName)");
        return new SplitPlaceholderRule.Builder(setEmptySet, component).setTag(tag).setMinWidthDp(minWidthDp).setMinHeightDp(minHeightDp).setMinSmallestWidthDp(minSmallestWidthDp).setMaxAspectRatioInPortrait(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(maxAspectRatioInPortrait)).setMaxAspectRatioInLandscape(EmbeddingAspectRatio.INSTANCE.buildAspectRatioFromValue$window_release(maxAspectRatioInLandscape)).setSticky(stickyPlaceholder).setFinishPrimaryWithPlaceholder(SplitRule.FinishBehavior.INSTANCE.getFinishBehaviorFromValue$window_release(finishPrimaryWithPlaceholder)).setDefaultSplitAttributes(defaultAttrs).build();
    }

    private final SplitPairFilter parseSplitPairFilter(Context context, XmlResourceParser parser) {
        TypedArray $this$parseSplitPairFilter_u24lambda_u243 = context.getTheme().obtainStyledAttributes(parser, R.styleable.SplitPairFilter, 0, 0);
        Object primaryActivityName = $this$parseSplitPairFilter_u24lambda_u243.getString(R.styleable.SplitPairFilter_primaryActivityName);
        Object secondaryActivityIntentName = $this$parseSplitPairFilter_u24lambda_u243.getString(R.styleable.SplitPairFilter_secondaryActivityName);
        String string = $this$parseSplitPairFilter_u24lambda_u243.getString(R.styleable.SplitPairFilter_secondaryActivityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        ComponentName primaryActivityClassName = buildClassName(packageName, (CharSequence) primaryActivityName);
        ComponentName secondaryActivityClassName = buildClassName(packageName, (CharSequence) secondaryActivityIntentName);
        return new SplitPairFilter(primaryActivityClassName, secondaryActivityClassName, string);
    }

    private final ActivityRule parseActivityRule(Context context, XmlResourceParser parser) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(parser, R.styleable.ActivityRule, 0, 0);
        String tag = typedArray.getString(R.styleable.ActivityRule_tag);
        boolean alwaysExpand = typedArray.getBoolean(R.styleable.ActivityRule_alwaysExpand, false);
        typedArray.recycle();
        ActivityRule.Builder builder = new ActivityRule.Builder(SetsKt.emptySet()).setAlwaysExpand(alwaysExpand);
        if (tag != null) {
            builder.setTag(tag);
        }
        return builder.build();
    }

    private final ActivityFilter parseActivityFilter(Context context, XmlResourceParser parser) {
        TypedArray $this$parseActivityFilter_u24lambda_u245 = context.getTheme().obtainStyledAttributes(parser, R.styleable.ActivityFilter, 0, 0);
        Object activityName = $this$parseActivityFilter_u24lambda_u245.getString(R.styleable.ActivityFilter_activityName);
        String string = $this$parseActivityFilter_u24lambda_u245.getString(R.styleable.ActivityFilter_activityAction);
        String packageName = context.getApplicationContext().getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "packageName");
        return new ActivityFilter(buildClassName(packageName, (CharSequence) activityName), string);
    }

    private final ComponentName buildClassName(String pkg, CharSequence clsSeq) {
        if (clsSeq == null || clsSeq.length() == 0) {
            throw new IllegalArgumentException("Activity name must not be null");
        }
        String cls = clsSeq.toString();
        char c = cls.charAt(0);
        if (c == '.') {
            return new ComponentName(pkg, pkg + cls);
        }
        String pkgString = pkg;
        String clsString = cls;
        int pkgDividerIndex = StringsKt.indexOf$default((CharSequence) cls, '/', 0, false, 6, (Object) null);
        if (pkgDividerIndex > 0) {
            String strSubstring = cls.substring(0, pkgDividerIndex);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            pkgString = strSubstring;
            String strSubstring2 = cls.substring(pkgDividerIndex + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
            clsString = strSubstring2;
        }
        if (!Intrinsics.areEqual(clsString, "*") && StringsKt.indexOf$default((CharSequence) clsString, '.', 0, false, 6, (Object) null) < 0) {
            return new ComponentName(pkgString, pkgString + '.' + clsString);
        }
        return new ComponentName(pkgString, clsString);
    }
}
