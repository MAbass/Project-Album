# Project-Album
Just add the code sql <br/>
**"ALTER TABLE `java_ee`.`userAlbum` 
DROP FOREIGN KEY `FK_userAlbum_albumUserId`;
ALTER TABLE `java_ee`.`userAlbum` 
ADD CONSTRAINT `FK_userAlbum_albumUserId`
  FOREIGN KEY (`albumUserId`)
  REFERENCES `java_ee`.`album` (`ID`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;"**<br/>
  after execute this project <br/>
  This part of code make the table **userAlbum** on cascade delete
