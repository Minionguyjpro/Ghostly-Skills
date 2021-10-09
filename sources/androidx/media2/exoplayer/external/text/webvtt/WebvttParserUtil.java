package androidx.media2.exoplayer.external.text.webvtt;

import androidx.media2.exoplayer.external.ParserException;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebvttParserUtil {
    private static final Pattern COMMENT = Pattern.compile("^NOTE(( |\t).*)?$");

    public static void validateWebvttHeaderLine(ParsableByteArray parsableByteArray) throws ParserException {
        int position = parsableByteArray.getPosition();
        if (!isWebvttHeaderLine(parsableByteArray)) {
            parsableByteArray.setPosition(position);
            String valueOf = String.valueOf(parsableByteArray.readLine());
            throw new ParserException(valueOf.length() != 0 ? "Expected WEBVTT. Got ".concat(valueOf) : new String("Expected WEBVTT. Got "));
        }
    }

    public static boolean isWebvttHeaderLine(ParsableByteArray parsableByteArray) {
        String readLine = parsableByteArray.readLine();
        return readLine != null && readLine.startsWith("WEBVTT");
    }

    public static long parseTimestampUs(String str) throws NumberFormatException {
        String[] splitAtFirst = Util.splitAtFirst(str, "\\.");
        long j = 0;
        for (String parseLong : Util.split(splitAtFirst[0], ":")) {
            j = (j * 60) + Long.parseLong(parseLong);
        }
        long j2 = j * 1000;
        if (splitAtFirst.length == 2) {
            j2 += Long.parseLong(splitAtFirst[1]);
        }
        return j2 * 1000;
    }

    public static Matcher findNextCueHeader(ParsableByteArray parsableByteArray) {
        String readLine;
        while (true) {
            String readLine2 = parsableByteArray.readLine();
            if (readLine2 == null) {
                return null;
            }
            if (COMMENT.matcher(readLine2).matches()) {
                do {
                    readLine = parsableByteArray.readLine();
                    if (readLine == null) {
                        break;
                    }
                } while (readLine.isEmpty());
            } else {
                Matcher matcher = WebvttCueParser.CUE_HEADER_PATTERN.matcher(readLine2);
                if (matcher.matches()) {
                    return matcher;
                }
            }
        }
    }
}
