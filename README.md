LicenseAdapter
===

Adapter library for RecyclerView to display your app's OSS dependencies.

This library fetch license text from GitHub(and your custom location), so technically this library is capable of displaying any license.


## Usage


Don't for get to add `android.permission.INTERNET` permission to your AndroidManifest.


```java
// create list of licenses
List<LicenseEntry> dataset = new ArrayList<>();

dataset.add(Licenses.fromGitHub("google/dagger"));
dataset.add(Licenses.fromGitHub("jakewharton/timber"));
dataset.add(Licenses.fromGitHub("reactivex/rxjava", Licenses.LICENSE_NO_EXTENSION));
dataset.add(Licenses.fromGitHub("reactivex/rxandroid", Licenses.LICENSE_NO_EXTENSION));
dataset.add(Licenses.fromGitHub("realm/realm-java", Licenses.LICENSE_NO_EXTENSION));
dataset.add(Licenses.fromGitHub("square/retrofit"));
dataset.add(Licenses.fromGitHub("yshrsmz/simple-preferences"));

// create adapter
LicenseAdapter adapter = new LicenseAdapter(dataset);
RecyclerView list = (RecyclerView) findViewById(R.id.list);
list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
list.setAdapter(adapter);

// finally load license text from Web
Licenses.load(dataset);
```
