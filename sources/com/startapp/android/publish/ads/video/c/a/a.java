package com.startapp.android.publish.ads.video.c.a;

/* compiled from: StartAppSDK */
public enum a {
    ErrorNone(0),
    XMLParsingError(100),
    SchemaValidationError(101),
    VersionOfResponseNotSupported(102),
    TraffickingError(200),
    VideoPlayerExpectingDifferentLinearity(201),
    VideoPlayerExpectingDifferentDuration(202),
    VideoPlayerExpectingDifferentSize(203),
    AdCategoryRequired(204),
    GeneralWrapperError(300),
    WrapperTimeout(301),
    WrapperLimitReached(302),
    WrapperNoReponse(303),
    InlineResponseTimeout(304),
    GeneralLinearError(400),
    FileNotFound(401),
    TimeoutMediaFileURI(402),
    MediaNotSupported(403),
    MediaFileDisplayError(405),
    MezzanineNotPovided(406),
    MezzanineDownloadInProgrees(407),
    ConditionalAdRejected(408),
    InteractiveCreativeFileNotExecuted(409),
    VerificationNotExecuted(410),
    MezzanineNotAsExpected(411),
    GeneralNonLinearAdsError(500),
    CreativeTooLarge(501),
    ResourceDownloadFailed(502),
    NonLinearResourceNotSupported(503),
    GeneralCompanionAdsError(600),
    CompanionTooLarge(601),
    CompanionNotDisplay(602),
    CompanionFetchFailed(603),
    CompanionNotSupported(604),
    UndefinedError(900),
    GeneralVPAIDerror(901),
    SAShowBeforeVast(10000),
    SAProcessSuccess(20000);
    
    private int value;

    private a(int i) {
        this.value = i;
    }

    public int a() {
        return this.value;
    }
}
