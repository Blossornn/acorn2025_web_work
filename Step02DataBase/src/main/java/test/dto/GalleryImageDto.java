package test.dto;

public class GalleryImageDto {
 private int num;
 // gallery 테이블 pk 참조
 private int GalleryNum;
 private String saveFileName;
 private String createdAt;
 public int getNum() {
	return num;
 }
 public void setNum(int num) {
	this.num = num;
 }
 public int getGalleryNum() {
	return GalleryNum;
 }
 public void setGalleryNum(int galleryNum) {
	GalleryNum = galleryNum;
 }
 public String getSaveFileName() {
	return saveFileName;
 }
 public void setSaveFileName(String saveFileName) {
	this.saveFileName = saveFileName;
 }
 public String getCreatedAt() {
	return createdAt;
 }
 public void setCreatedAt(String createdAt) {
	this.createdAt = createdAt;
 }
 
 
}
