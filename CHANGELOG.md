## unreleased

### Change

- Deprecate `Licenses#fromGitHub(String)` and `Licenses#fromGitHub(String, String)`, since these method implicitly use Apache v2 as default license.

### Enhancement

- Add `Licenses#fromGitHubApacheV2(String)`, `Licenses#fromGitHubApacheV2(String, String)`, `Licenses#fromGitHubMIT(String)`, `Licenses#fromGitHubMIT(String, String)`, `Licenses#fromGitHubBSD(String)`, `Licenses#fromGitHubBSD(String, String)`.  
  For other licenses, use `Licenses#fromGitHub(String)` and `Licenses#fromGitHub(String, String, String)`, the same as before.


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
