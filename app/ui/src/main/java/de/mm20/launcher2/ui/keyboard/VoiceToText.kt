package de.mm20.launcher2.ui.keyboard

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VoiceToText(
    private val app: Context
) : RecognitionListener {

    private val _state = MutableStateFlow(VoiceToTextParserState())
    val state get() = _state.asStateFlow()

    private val recognizer = SpeechRecognizer.createSpeechRecognizer(app)

    fun startListening(languageCode: String) {
        _state.update { VoiceToTextParserState() }

        if (!SpeechRecognizer.isRecognitionAvailable(app)) {
            _state.update {
                it.copy(error = "Speech recognition is not available on this device")
            }
            return Toast.makeText(app, "Speech recognition is not available on this device", Toast.LENGTH_SHORT).show()
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
        }
        _state.update {
            it.copy(
                isSpeaking = true
            )
        }

        recognizer.setRecognitionListener(this)
        recognizer.startListening(intent)
    }

    fun stopListening() {
        _state.update { it.copy(isSpeaking = false) }
        recognizer.stopListening()
        //recognizer.cancel()
    }

    override fun onReadyForSpeech(p0: Bundle?) {
        _state.update { it.copy(error = null) }
    }

    override fun onBeginningOfSpeech() {
        _state.update { it.copy(isSpeaking = true) }
    }

    override fun onEndOfSpeech() {
        _state.update { it.copy(isSpeaking = false) }
    }

    override fun onError(error: Int) {
        if (error == SpeechRecognizer.ERROR_CLIENT) {
            return
        } else {
            _state.update { it.copy(error = "Error code: $error", isSpeaking = false) }
        }
    }

    override fun onResults(result: Bundle?) {
        result?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.getOrNull(0)?.let { results ->
            _state.update { it.copy(spokenText = results) }
        }
    }

    override fun onPartialResults(p0: Bundle?) = Unit
    override fun onRmsChanged(p0: Float) = Unit
    override fun onBufferReceived(p0: ByteArray?) = Unit
    override fun onEvent(p0: Int, p1: Bundle?) = Unit
}

data class VoiceToTextParserState(
    val spokenText:String = "",
    val isSpeaking:Boolean = false,
    val error : String? = null
)