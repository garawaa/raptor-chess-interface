/**
 * New BSD License
 * http://www.opensource.org/licenses/bsd-license.php
 * Copyright 2011 RaptorProject (http://code.google.com/p/raptor-chess-interface/)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name of the RaptorProject nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package raptor.swt.chess.controller;

import org.eclipse.swt.widgets.Display;

import raptor.service.ThreadService;

public class AutomaticAnalysisController {
	private InactiveController boardController;

	public AutomaticAnalysisController(InactiveController boardController) {
		this.boardController = boardController;
	}
	
	public void startAnalysis(int secsPerMove, float threshold) {	
		ThreadService.getInstance().run(new Runnable() {

			@Override
			public void run() {
				boardController.getBoard().getControl().getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {	
						boardController.getBoard().showEngineAnalysisWidget();						
					}
					
				});
				int nOfMoves = boardController.getGame().getMoveList().getSize();
				for (int i = 0; i < nOfMoves; i++) {
					final int ii = i;
					boardController.getBoard().getControl().getDisplay().syncExec(new Runnable() {

						@Override
						public void run() {
							boardController.gotoMove(ii);
						}
						
					});
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {	}
				}
				
			}
			
		});
		
		
			}
	
	
}