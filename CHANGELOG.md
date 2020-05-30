## 3.0.1 - 2020/05/30

### Changes

- Update to Android Gradle Plugin 3.6.3
- Update to androidx.browser v1.2.0
- Migrate to lifecycle-viewmodel as lifecycle-extensions was deprecated.


## 3.0.0 - 2019/12/10

### Breaking Changes

- Migrate to AndroidX
- API 29


## 2.2.2 - 2018/10/23

### Changes

- Display loading state

### Bug fixes

- License content from previously displayed library is displayed wrongly while loading actual license content.

## 2.2.1 - 2018/09/30

### Changes

- Update to Android Support Library 28.0.0. This is the last release with Android Support Library.
- Migrate to gradle-bintray-plugin from novoda/bintray-release.

## 2.2.0 - 2018/09/30

- test release with gradle-bintray-plugin(not working)


## 2.1.2 - 2018/08/17

### Bug fixes

- Fix numerous bugs on pre-L devices
  - Incorrectly colored library titles
  - Incorrectly styled text
  - Crash loading drawables
- Fix bug where license wouldn't always display on first load


## 2.1.1 - 2018/07/10

### Bug fixes

- Fix crash retrieving attr. Primary and accent colors are now retrieved from the system attrs.
- Colorize all linkable library names


## 2.1.0 - 2018/06/02

### Bug fixes/Behavior changes

- Use Attributes to obtain `colorPrimary` and `colorAccent`, so that we don't need to declare color value named `colorPrimary` or `colorAccent` [#43](https://github.com/yshrsmz/LicenseAdapter/issues/43)


## 2.0.2 - 2017/12/14

### Bug fixes

- Artifact is broken [#39](https://github.com/yshrsmz/LicenseAdapter/issues/39)


## 2.0.1 - 2017/12/14

### Bug fixes

- Artifact is broken [#39](https://github.com/yshrsmz/LicenseAdapter/issues/39)



## 2.0.0 - 2017/12/14

### Breaking changes

Most breaking changes should not affect you unless you built custom
infrastructure around License Adapter model classes. Important changes
are noted in bold.

- **The `Licenses#load(List<LicenseEntry>)` method is no longer necessary
  and has been removed.**
- **The `LicenseEntry` has been renamed to `Library` to minimize
  confusion.**
  - The `LicenseAdapter`'s constructor takes in a list of `Library`s
    instead of `LicenseEntry`s.
- The standard AppCompat color resources (`colorPrimary`,
  `colorPrimaryDark`, `colorAccent`) are now required.
- Models no longer implement `Parcelable`, are final, and follow proper
  Java Bean getter/setter naming conventions.
- The `name()`, `version()`, and `link()` getters on the `LicenseEntry`
  are no longer present in the `Library` interface as they weren't being
  used.
- The `Licenses` entry point is now final and cannot be instantiated.
- `Library` implementation are no longer accessible. The `Licenses`
  class should cover all cases. If it doesn't, please file an issue!
- The `BaseLibrary` class now has a simple API which automatically
  performs in-memory and on-disk caching and can be extended if necessary.

### New features

- Completely revamped UI and improved UX
- On-demand license legal text loading instead of all at once when it
  isn't necessarily needed
- On-device and in-memory (config change resilient) caching for optimal
  performance
- Uses vector drawables to minimize resources
- Massively improved API surface

### Bug fixes

- Input streams weren't closed properly
- If license text took too long to load, it wouldn't be displayed


## 1.4.1 - 2017/09/XX

### Changes

- Update to SDK26
- Update to Support Library 26.1.0


## 1.4.0 - 2016/02/05

Thanks [@SUPERCILEX](https://github.com/SUPERCILEX) for adding great new features(#22, #25)!

### Change

- Deprecate `LicenseEntry#link` and add `LicenseEntry#url`(for naming consistency). [PR #27](https://github.com/yshrsmz/LicenseAdapter/pull/27)
- Update dependencies

### Enhancement

- Add `Licenses#FILE_AUTO` option which automatically check & load `LICENSE` file regardless of its extension. [PR #22](https://github.com/yshrsmz/LicenseAdapter/pull/22)
- `Licenses#fromGitHubApacheV2(String)`, `Licenses#fromGitHubMIT(String)` and `Licenses#fromGitHubBSD(String)` now check & load appropriate "LICENSE" file automatically. [PR #22](https://github.com/yshrsmz/LicenseAdapter/pull/22)
  While this feature is convenient, it can increase network access. If you are certain about LICENSE file name and don't mind doing extra work, I recommend you to specify license filename explicitly.
- Add `NoLinkLicenseEntry` to display a license of which content is provided as a String. [PR #25](https://github.com/yshrsmz/LicenseAdapter/pull/24)


## 1.3.0 - 2016/01/09

### Change

- Deprecate `Licenses#fromGitHub(String)` and `Licenses#fromGitHub(String, String)`, since these methods implicitly use Apache v2 as default license.

### Enhancement

- Add `Licenses#fromGitHubApacheV2(String)`, `Licenses#fromGitHubApacheV2(String, String)`, `Licenses#fromGitHubMIT(String)`, `Licenses#fromGitHubMIT(String, String)`, `Licenses#fromGitHubBSD(String)`, `Licenses#fromGitHubBSD(String, String)`.
  For other licenses, use `Licenses#fromGitHub(String, License)` and `Licenses#fromGitHub(String, String, String)`, the same as before.


## 1.2.4 - 2016/06/02

### Fix

- Fix incorrect Parcelable inheritance. LicenseEntry now inherits Parcelable. [[issue #8](https://github.com/yshrsmz/LicenseAdapter/issues/8)]



## 1.2.3 - 2016/05/27

### Fix

- When expanding the last two licenses, the second license appears above the header, and cannot be unexpanded. [[issue #7](https://github.com/yshrsmz/LicenseAdapter/issues/7)]



## 1.2.2 - 2016/05/26

### Fix

- Header not updated when collapsed via content click [issue #6]


## 1.2.1 - 2016/05/06

### Fix

- Fatal Exception in pre-Lollipop device [issue #5]


## 1.2.0 - 2016/05/03

### Change

- License name constants are renamed. `Licenses#LICENSE_NAME_XXX` -> `Licenses#NAME_XXX`
- License file name constants are renamed `Licenses#LICENSE_XXX` -> `Licenses#FILE_XXX`


## 1.1.1 - 2016/04/30

### Enhancement

- Tint arrow icon with `?attr/colorControlNormal` to support both Light & Dark theme.


## 1.1.0 - 2016/04/29

### Fix

- can't display license content of the last item when the RecyclerView is placed in dialog
- weired scrolling behaviour while trying to expand item


## 1.0.2 - 2016/04/24

### Fix

- Update github raw content url



## 1.0.1 - 2016/04/22

### Enhancement

- Remove license type TextView when no type is provided.


## 1.0.0 - 2016/04/22

initial release
