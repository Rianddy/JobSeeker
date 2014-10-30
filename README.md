Job Seeker
============

Requirements:

This project needs to implement some functions like Job Alert Feed of Glassdoor.com. So it needs a list to show those job information. It also needs a search engine to return related jobs including key words. Furthermore, cart module is also required so that it can help users to manage applying jobs.

============

Implementation:

Firstly, I worte a crawler based on DOM to get as many jobs as I can such as job title, content, time etc. 

Then I used Lucene to build index for those job information and used the basic tf*idf model to return similar job according to some key words. 

Although this was a small system at that time, I used Struts, Spring and Hibernate frameworks to make it extensible and easily maintainable. So all the codes is clean and tidy. As to interface design, jquery was used.

Moreover, I achieved privilege access module in this system by taking advantage of AOP programming of spring and Interceptor. Everytime a user wants to log in, the system checks the session in interceptor or check the md5 of password @before excuting method. Another amazing part of this module is that, the system can record which jobs you choose. That means it can merge these jobs with your cart history after you logged in like the Amazon's cart module. To achieve this, I made full use of cookie and database. 

============

Limitation:

1. The feed can not be fully customized. 
2. The mechanism of sending feed is not optimized enough. In future, observer pattern will be a good choice to be applied into this project. For example, rather than checking feed or new jobs within regular period, it would be much better to check user's customized feed once there are a certain number of new jobs coming in.
3. The job information cannot be updated automatically. To update jobs by crawling internet is kind of hard.

============

Rianddy

rianddy@gmail.com
