package googledocs;

public class Main {
    public static void main(String[] args) {
        VersionManager manager = new VersionManager();
        Document doc = new Document("doc.txt");
        DocUser user = new DocUser("prashant", doc);
        DocUser user2 = new DocUser("sandeep", doc);
        DocUser user3 = new DocUser("neeraj", doc);

        doc.updatedLine(1, user);
        
        manager.add(doc.saveDocumentVersion());//version 1
        doc.updatedLine(2, user2);
        manager.add(doc.saveDocumentVersion()); //version 2
        doc.updatedLine(1,user3);
        manager.add(doc.saveDocumentVersion());// version 3
        doc.restoreState(manager.getVersion(1));
        doc.restoreState(manager.getVersion(2));
        System.out.println(doc);


    }
}
