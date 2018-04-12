### moksiskaan-populator
This is the final code for the moksiskaan populator

The term ‘database’ refers to the collection of data. There has been huge impact of databases in our day-to-day life. In simple terms, database is more like a library where books have been placed, governed with specific rules and orders, so, a user can easily find. In digital world of today, minute to complex information are being stored on databases and access to it is being provided by Database management system(DBMS).  DBMS provides various functionality from retrieving, storing, manipulating data inside database. There are plenty of database application in our daily lives e.g. flight booking system, online shopping stores, online exam portals, Enterprise resource planning systems, Customer relation manager systems, social networks and so on. Based on the implementation of data storage, it could be relational databases or non-relational. Database implementation has been proved quite a beneficial in cell biology to show complex relations between different nodes, components and its entities.

Moksiskaan is a generic database and a toolkit (http://csbi.ltdk.helsinki.fi/moksiskaan/) that is used to integrate information about the connections between genes, proteins, pathways, drugs, and other biological entities. It combines various existing databases to find biological relationships between the genes of interest and to predict their interactions[ii]. The Moksiskaan database is installed on the user's server and is populated from such public databases as KEGG, WikiPathways and Pathway Commons. Currently, Moksiskaan acquisition functionality is not updated to the current specifications of WikiPathways and Pathway Commons. KEGG Database usage is restricted by paid licenses. Hereby, the goal is to implement new data acquisition methods to import fresh data for Moksiskaan database from WikiPathways and Pathway Commons.

This process of importing, modifying and populating the in-house Moksiskaan database is error prone to carryout manually. Therefore, this process is automated using selenium web driver. Selenium webdriver interacts with web pages like humans and it does not depend on dedicated server to run the tests. It can click links, buttons and fill out forms. Instead of using browser-based JavaScript commands to run the browser, it uses its own native operating system to send commands to a browser. Apart from these above-mentioned features, it has improved efficiency and required number of calls to the API on newer version. Therefore, the selenium was used to automate the process, in order to achieve the goal of the project as well as to provide smooth user experience.

