#script to automate my repository update with MAKEFILE
run:
	git status
	git add .
		@echo "Add the commit message:"; \
	read COMMITMESSAGE; \
	git commit -m " $$COMMITMESSAGE "
		git push origin
		echo "<<<<<<<<<<--- REPOSITORY UPDATED SUCCESSFUL!! --->>>>>>>>>>"
	git log -n 3
#		@echo "Enter the name of branch (main)"; \
#read BRANCH; \
#git push origin $$BRANCH; \
#git push origin $$BRANCH; \
