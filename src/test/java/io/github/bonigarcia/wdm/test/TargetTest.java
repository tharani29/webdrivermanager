/*
 * (C) Copyright 2016 Boni Garcia (http://bonigarcia.github.io/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
package io.github.bonigarcia.wdm.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.Downloader;

/**
 * Target folder test.
 *
 * @author Boni Garcia (boni.gg@gmail.com)
 * @since 1.4.1
 */
@RunWith(Parameterized.class)
public class TargetTest {

	protected static final Logger log = LoggerFactory
			.getLogger(TargetTest.class);

	@Parameter(0)
	public String version;

	@Parameter(1)
	public String url;

	@Parameter(2)
	public String target;

	private static String targetPath = Downloader.getTargetPath();

	@Parameters(name = "{index}: {0}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				// Chrome Linux
				{ "2.21",
						"http://chromedriver.storage.googleapis.com/2.21/chromedriver_linux64.zip",
						"/chromedriver/linux64/2.21/chromedriver_linux64.zip" },

				// Opera Linux
				{ "0.2.2",
						"https://github.com/operasoftware/operachromiumdriver/releases/download/v0.2.2/operadriver_linux64.zip",
						"/operadriver/linux64/0.2.2/operadriver_linux64.zip" },

				// PhantomJS Linux
				{ "2.1.1",
						"https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-2.1.1-linux-x86_64.tar.bz2",
						"/phantomjs/linux-x86_64/2.1.1/phantomjs-2.1.1-linux-x86_64.tar.bz2" },

				// PhantomJS Windows
				{ "2.1.1",
						"https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-2.1.1-windows.zip",
						"/phantomjs/windows/2.1.1/phantomjs-2.1.1-windows.zip" },

				// Edge Windows
				{ "8D0D08CF-790D-4586-B726-C6469A9ED49C",
						"https://download.microsoft.com/download/1/4/1/14156DA0-D40F-460A-B14D-1B264CA081A5/MicrosoftWebDriver.exe",
						"/MicrosoftWebDriver/8D0D08CF-790D-4586-B726-C6469A9ED49C/MicrosoftWebDriver.exe" },

				// Marionette Linux
				{ "0.6.2",
						"https://github.com/jgraham/wires/releases/download/v0.6.2/wires-0.6.2-linux64.gz",
						"/wires/linux64/0.6.2/wires-0.6.2-linux64.gz" }

		});

	}

	@Test
	public void testTarget() throws IOException {
		String result = Downloader.getTarget(version, new URL(url));
		log.info(result);
		log.info(targetPath + target);

		File fileResult = new File(result);
		File fileReal = new File(targetPath + target);

		assertThat(fileResult, equalTo(fileReal));
	}

}
