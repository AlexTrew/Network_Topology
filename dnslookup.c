//use getaddrinfo

//replace connect with inet_top
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <arpa/inet.h>




int
dnslookup(char *address){
	
	struct addrinfo hints, *ai, *ai0;
	int i;


	//look up IP address of the hostname
	memset(&hints,0,sizeof(hints));
	hints.ai_family = PF_UNSPEC;
	hints.ai_socktype = SOCK_STREAM;

	char ip_address[INET6_ADDRSTRLEN];

	if((i = getaddrinfo(address,"80",&hints,&ai0)) != 0){
		printf("Unable to look up IP address : %s\n", gai_strerror(i));
		return 2;
	} 

	for(ai = ai0; ai != NULL; ai=ai->ai_next){

		if(ai->ai_family == AF_INET){
			inet_ntop(AF_INET,&((struct sockaddr_in*)ai->ai_addr)->sin_addr,ip_address,sizeof(ip_address));
			printf("%s IPV4 %s\n", address, ip_address);
			break;

		}
		if(ai->ai_family == AF_INET6){
			inet_ntop(AF_INET6,&((struct sockaddr_in*)ai->ai_addr)->sin_addr,ip_address,sizeof(ip_address));
			printf("%s IPV6 %s\n", address, ip_address);
		}	
		else{
			printf("address error: address not ipv4 or ipv6\n");
			break;
		}
	}



	return 0;
}




int 
main(int argc, char *argv[]){
	for(int i=1; i<argc;i++){
		dnslookup( argv[i]);
	}
}