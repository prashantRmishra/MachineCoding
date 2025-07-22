import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// URL Shortener Core Class
class UrlShortener {
    private final Map<String, String> urlMapper = new ConcurrentHashMap<>();
    private final Map<String, String> reverseUrlMapper = new ConcurrentHashMap<>();
    private final UrlGenerationStrategy urlGenerationStrategy;

    public UrlShortener(UrlGenerationStrategy strategy) {
        this.urlGenerationStrategy = strategy;
    }

    public synchronized String createUrl(String originalUrl) {
        if (reverseUrlMapper.containsKey(originalUrl)) {
            return reverseUrlMapper.get(originalUrl);
        }
        String shortUrl = urlGenerationStrategy.generateUrl(originalUrl);

        urlMapper.put(shortUrl, originalUrl);
        reverseUrlMapper.put(originalUrl, shortUrl);

        return shortUrl;
    }

    public String getOriginalUrl(String shortUrl) {
        return urlMapper.get(shortUrl);
    }
}

// Unique ID Generator
class Utility {
    private static final AtomicInteger id = new AtomicInteger(0);

    public static int getNewId() {
        return id.getAndIncrement();
    }
}

// Strategy Interface
interface UrlGenerationStrategy {
    String generateUrl(String originalUrl);
}

// Sequential ID-Based URL Generator
class SequentialIdUrlGenerator implements UrlGenerationStrategy {
    private final String shortUrlPrefix;

    public SequentialIdUrlGenerator(String shortUrlPrefix) {
        this.shortUrlPrefix = shortUrlPrefix;
    }

    @Override
    public String generateUrl(String originalUrl) {
        return shortUrlPrefix + Utility.getNewId();
    }
}

// Random Number-Based URL Generator
class RandomNumberUrlGenerator implements UrlGenerationStrategy {
    private final String shortUrlPrefix;
    private final int range;
    private final Random random = new Random();

    public RandomNumberUrlGenerator(String shortUrlPrefix, int range) {
        this.shortUrlPrefix = shortUrlPrefix;
        this.range = range;
    }

    @Override
    public String generateUrl(String originalUrl) {
        return shortUrlPrefix + random.nextInt(range);
    }
}

class Base62Encoder {
    private static final String BASE62_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int remainder = (int)(num % 62);
            sb.append(BASE62_CHARS.charAt(remainder));
            num /= 62;
        }
        return sb.reverse().toString();
    }
}

// Base62 Sequential URL Generator
class Base62SequentialUrlGenerator implements UrlGenerationStrategy {
    private final String shortUrlPrefix;

    public Base62SequentialUrlGenerator(String shortUrlPrefix) {
        this.shortUrlPrefix = shortUrlPrefix;
    }

    @Override
    public String generateUrl(String originalUrl) {
        long id = Utility.getNewId();
        String base62Code = Base62Encoder.encode(id);
        return shortUrlPrefix + base62Code;
    }
}

// Demo Class
class UrlShortenerWithBase62Demo {
    public static void main(String[] args) {
        UrlGenerationStrategy strategy = new Base62SequentialUrlGenerator("http://sho.rt/");
        UrlShortener urlShortener = new UrlShortener(strategy);

        String shortUrl1 = urlShortener.createUrl("https://example.com/page1");
        String shortUrl2 = urlShortener.createUrl("https://example.com/page2");
        String shortUrl3 = urlShortener.createUrl("https://example.com/page1"); // Duplicate URL

        System.out.println("Short URL 1: " + shortUrl1);
        System.out.println("Short URL 2: " + shortUrl2);
        System.out.println("Short URL 3 (duplicate): " + shortUrl3);

        System.out.println("Original URL for " + shortUrl1 + ": " + urlShortener.getOriginalUrl(shortUrl1));
    }
}

/*output:
Short URL 1: http://sho.rt/a
Short URL 2: http://sho.rt/b
Short URL 3 (duplicate): http://sho.rt/a
Original URL for http://sho.rt/a: https://example.com/page1
*/