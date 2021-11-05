QUICK START:

If the installer did not do it for you, start Freenet and open a browser pointing
to http://localhost:8888/

INTRODUCTION:

The Freenet Project is very pleased to announce the release of Freenet 0.7.0.

Freenet is software designed to allow the free exchange of information over the 
Internet without fear of censorship, or reprisal. To achieve this Freenet makes it 
very difficult for adversaries to reveal the identity, either of the person 
publishing, or downloading content. The Freenet project started in 1999, released 
Freenet 0.1 in March 2000, and has been under active development ever since.

Freenet is unique in that it handles the storage of content, meaning that if 
necessary users can upload content to Freenet and then disconnect. We've 
discovered that this is a key requirement for many Freenet users. Once uploaded, 
content is mirrored and moved around the Freenet network, making it very difficult 
to trace, or to destroy. Content will remain in Freenet for as long as people are 
retrieving it, although Freenet makes no guarantee that content will be stored 
indefinitely.

The journey towards Freenet 0.7 began in 2005 with the realization that some of 
Freenet's most vulnerable users needed to hide the fact that they were using 
Freenet, not just what they were doing with it. The result of this realization was a 
ground-up redesign and rewrite of Freenet, adding a "darknet" capability, allowing 
users to limit who their Freenet software would communicate with to trusted friends. 
This would make it far more difficult for a third-party to determine who is using 
Freenet.

Freenet 0.7 also embodies significant improvements to almost every other aspect of 
Freenet, including efficiency, security, and usability. Freenet is available for Windows, 
Linux, and OSX. It can be downloaded from:

http://freenetproject.org/download.html

If you have any difficulty getting Freenet to work, or any questions not answered in the 
faq, please join us on IRC in the #freenet channel at irc.freenode.net. Thank you.

This release would not have been possible without the efforts of numerous volunteers, and 
Matthew Toseland, Freenet's full time developer. Matthew's work is funded through donations 
via our website (as well as a few larger sponsors from time to time). We ask that anyone 
who can help us to ensure Matthew's continued employment visit our donations page and 
make a contribution at:

http://freenetproject.org/donate.html

Press enquiries should be directed to Ian Clarke.

ALWAYS ON:
The installer will load the system tray applet (the rabbit icon on the bottom left) when
you log in. We strongly recommend you run Freenet as close to 24x7 as possible for good
performance. However, you can remove this from the startup menu if you don't want this 
to happen. Also, you can easily use the tray icon to turn Freenet on and off when you
need to free up bandwidth and CPU. However, bear in mind that unless you are regularly
downloading and uploading large files, Freenet will use most system resources just after
you start it up, so you should avoid this where possible.

BASIC SECURITY:
The easiest option is to use the system tray applet to launch Freenet. This will try to
load a browser (Chrome or Firefox) in privacy/incognito mode. If the browser is in privacy
mode it will tell you. Browser bugs might result in it not being in privacy mode so be
careful of this.

You MUST use a separate browser to access Freenet than the one you use to access
the WWW at large (or use one with privacy mode enabled). Browser history stealing attacks
enable malicious websites to probe for specific freesites you have visited. It may also
be possible for hostile websites to probe for the existence of Freenet by javascript port
scanning or similar attacks, or possibly even time loads of specific pages from Freenet,
from the browser you use for the web; this last attack is unconfirmed at the time of 
writing.

MORE SECURITY:
If your life or liberty depends on Freenet protecting your anonymity, you should
seriously evaluate your options, including the option of not posting whatever
controversial content it is you are thinking of posting. Freenet has not yet
reached version 1.0, and several important security features have not yet been
implemented; there are several known attacks which future versions will greatly
reduce, and there are likely to be (and have been) serious bugs. If you do 
choose to use Freenet under such circumstances, you  should enable the MAXIMUM 
network security level and add connections to your friends on the Friends page; 
connecting only to friends considerably improves your security against a variety 
of attacks, but you should only connect to them if you know them or have some 
reason to (at least minimally) trust them; connecting to arbitrary strangers 
from IRC may end up with you adding the bad guys as Friends, and apart from that 
it damages the network topology.

CHANGES FROM 0.5:
This is the 0.7 rewrite of Freenet. This is largely rewritten from scratch, 
although it pulls in a load of code from Dijjer, and most of the crypto and a 
few other classes from the 0.5 source.

Major changes:
- Darknet mode: connect only to your friends, they connect to theirs, this forms
  a small-world network, which Freenet makes routable by location swapping. This
  greatly increases the network's robustness as it makes it much harder to find
  and block Freenet nodes on a national firewall, as well as improving security
  generally provided that your friends are trustworthy. 
- Opennet mode (plug and play) is also supported. Just select network security
  level NORMAL or LOW in the first-time wizard.
- Freenet now uses UDP, mainly to improve connectivity over NATs and firewalls.
- Freenet now uses 32kB fixed block sizes, to improve performance and simplify 
  the code.
- The Freenet Client Protocol is completely different, see the spec here:
  http://wiki.freenetproject.org/FreenetFCPSpec2Point0
- Many more changes...

LICENSING:
Freenet is under the GPLv2 - see LICENSE.Freenet (we are trying to make this
GPL 2 or later, but some code is arguably GPL 2 only). However, we use some 
other code:
- Mantissa is under the modified BSD license. See README.Mantissa. According 
  to the FSF, modified BSD is compatible with GPL; we must include both 
  licenses.

UNINSTALLING:
Use a Add/Remove Programs in the Control Panel. You may need to shut down Freenet
using the tray icon first.
